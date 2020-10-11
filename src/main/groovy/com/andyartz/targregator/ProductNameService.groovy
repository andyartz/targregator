package com.andyartz.targregator

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

interface ProductNameService {

    String getProductName(Integer id)
}

class ProductNameNotFoundException extends ResponseStatusException {

    ProductNameNotFoundException(Exception e) {
        super(HttpStatus.NOT_FOUND, 'Product name not found', e)
    }
}
