package com.andyartz.targregator.mongo

import com.andyartz.targregator.ProductPricingNotFoundException
import com.andyartz.targregator.domain.Pricing
import spock.lang.Specification

class MongoProductPricingServiceSpec extends Specification {

    MongoProductPricingService systemUnderTest

    ProductRepository mockProductRepository = Mock()

    void setup() {
        systemUnderTest = new MongoProductPricingService(productRepository:mockProductRepository)
    }

    void "should get product pricing"() {
        given:
        mockProductRepository.findById(BigInteger.valueOf(1234)) >> Optional.of(Mock(ProductRecord) {
            it.currentPriceInUSDollars >> 750.01
        })

        when:
        Pricing actual = systemUnderTest.getProductPricing(1234)

        then:
        verifyAll(actual) {
            it.amount == 750.01
            it.currency.currencyCode == 'USD'
        }
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
