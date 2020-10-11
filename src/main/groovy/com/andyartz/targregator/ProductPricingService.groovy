package com.andyartz.targregator

import com.andyartz.targregator.domain.Pricing
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

interface ProductPricingService {

    Pricing getProductPricing(Integer integer)
}

class ProductPricingNotFoundException extends ResponseStatusException {

    ProductPricingNotFoundException(Exception e) {
        super(HttpStatus.NOT_FOUND, 'Product pricing not found', e)
    }
}
