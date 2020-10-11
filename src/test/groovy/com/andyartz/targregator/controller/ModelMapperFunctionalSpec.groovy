package com.andyartz.targregator.controller

import com.andyartz.targregator.domain.Pricing
import com.andyartz.targregator.domain.Product
import org.modelmapper.ModelMapper
import spock.lang.Specification

class ModelMapperFunctionalSpec extends Specification {

    ModelMapper systemUnderTest

    void setup() {
        systemUnderTest = new ProductControllerConfig().modelMapper
    }

    void 'should convert a Product to a ProductDto'() {
        given:
        Pricing pricing = new Pricing(amount:45.98, currency:Currency.getInstance('USD'))
        Product product = new Product(id:3006, name:'Voltron', currentPricing:pricing)

        when:
        ProductDto actual = systemUnderTest.map(product, ProductDto)

        then:
        actual == new ProductDto(
            id:3006,
            name:'Voltron',
            currentPricing:new PricingDto(amount:45.98, currency:Currency.getInstance('USD'))
        )
    }
}
