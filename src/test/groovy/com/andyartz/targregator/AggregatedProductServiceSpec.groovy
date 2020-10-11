package com.andyartz.targregator

import com.andyartz.targregator.domain.Pricing
import com.andyartz.targregator.domain.Product
import spock.lang.Specification

class AggregatedProductServiceSpec extends Specification {

    AggregatedProductService systemUnderTest

    ProductPricingService mockProductPricingService = Mock()
    ProductNameService mockProductNameService = Mock()

    void setup() {
        systemUnderTest = new AggregatedProductService(productNameService:mockProductNameService, productPricingService:mockProductPricingService)
    }

    void 'should get product'() {
        given:
        Pricing mockPricing = Mock()
        mockProductPricingService.getProductPricing(123) >> mockPricing

        and:
        mockProductNameService.getProductName(123) >> 'Any Name'

        when:
        Product actual = systemUnderTest.getProduct(123)

        then:
        verifyAll(actual) {
            it.id == 123
            it.name == 'Any Name'
            it.currentPricing == mockPricing
        }
    }
}
