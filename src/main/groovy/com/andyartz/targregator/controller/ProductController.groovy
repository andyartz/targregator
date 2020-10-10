package com.andyartz.targregator.controller

import com.andyartz.targregator.ProductService
import com.andyartz.targregator.domain.Pricing
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController {

    @Autowired
    ProductService productService

    @Autowired
    ModelMapper modelMapper

    @GetMapping(path = '/products/{id}', produces = MediaType.APPLICATION_JSON_VALUE)
    ProductDto getProduct(@PathVariable Integer id) {
        productService.getProduct(id).with {
            modelMapper.map(it, ProductDto)
        }
    }
}

class ProductDto {
    Integer id
    String name
    Pricing currentPricing
}

class PricingDto {
    BigDecimal amount
    Currency currency
}
