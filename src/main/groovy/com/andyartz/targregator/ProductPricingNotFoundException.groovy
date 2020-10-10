package com.andyartz.targregator

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class ProductPricingNotFoundException extends ResponseStatusException {

    ProductPricingNotFoundException(Exception e) {
        super(HttpStatus.NOT_FOUND, 'Product pricing not found', e)
    }
}