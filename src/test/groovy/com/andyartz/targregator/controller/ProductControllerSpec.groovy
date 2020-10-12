package com.andyartz.targregator.controller

import com.andyartz.targregator.ProductService
import com.andyartz.targregator.domain.Product
import org.modelmapper.ModelMapper
import spock.lang.Specification

class ProductControllerSpec extends Specification {

    ProductController systemUnderTest

    ProductService mockProductService = Mock()
    ModelMapper mockModelMapper = Mock()

    void setup() {
        systemUnderTest = new ProductController(productService:mockProductService, modelMapper:mockModelMapper)
    }

    void "should get products"() {
        given:
        Product mockProduct = Mock()
        mockProductService.getProduct(-1000) >> mockProduct

        and:
        ProductDto mockProductDto = Mock()
        mockModelMapper.map(mockProduct, ProductDto) >> mockProductDto

        expect:
        systemUnderTest.getProduct(-1000) == mockProductDto
    }
}
