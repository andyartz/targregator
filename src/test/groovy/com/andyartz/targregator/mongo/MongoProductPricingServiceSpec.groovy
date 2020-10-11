package com.andyartz.targregator.mongo

import com.andyartz.targregator.ProductPricingNotFoundException
import com.andyartz.targregator.domain.Pricing
import spock.lang.Specification
import spock.lang.Unroll

class MongoProductPricingServiceSpec extends Specification {

    MongoProductPricingService systemUnderTest

    ProductRepository mockProductRepository = Mock()
    Currency mockCurrency = GroovyMock()

    void setup() {
        systemUnderTest = new MongoProductPricingService(productRepository:mockProductRepository, currency:mockCurrency)
    }

    @Unroll
    void "should get product pricing, rounded from #dbAmount to #expectedAmount for currency #currencyCode"() {
        given: 'repository has a price'
        mockProductRepository.findById(BigInteger.valueOf(1234)) >> Optional.of(Mock(ProductRecord) {
            it.currentPrice >> dbAmount
        })

        and: 'currency is set'
        systemUnderTest.currency = Currency.getInstance(currencyCode)

        when: 'price is retrieved'
        Pricing actual = systemUnderTest.getProductPricing(1234)

        then: 'price is returned with currency code and properly rounded'
        verifyAll(actual) {
            it.amount == expectedAmount
            it.currency.currencyCode == currencyCode
        }

        where:
        dbAmount  | currencyCode | expectedAmount
        750.01    | 'USD'        | 750.01
        750.01345 | 'USD'        | 750.01
        750.015   | 'USD'        | 750.02
        750.01345 | 'BHD'        | 750.013
        750.01345 | 'BIF'        | 750
    }

    void "should throw exception is no price found"() {
        given:
        mockProductRepository.findById(BigInteger.valueOf(67)) >> Optional.empty()

        when:
        systemUnderTest.getProductPricing(67)

        then:
        thrown(ProductPricingNotFoundException)
    }
}
