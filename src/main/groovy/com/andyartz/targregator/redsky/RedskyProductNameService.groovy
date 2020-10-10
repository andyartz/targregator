package com.andyartz.targregator.redsky

import com.andyartz.targregator.ProductNameNotFoundException
import com.andyartz.targregator.ProductNameService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.Cacheable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpClientErrorException

@Service
class RedskyProductNameService implements ProductNameService {

    @Autowired
    RedskyClient redskyClient

    @Cacheable('product-names')
    @Override
    String getProductName(Integer id) {
        try {
            redskyClient.getProduct(id).product.item.productDescription.title
        } catch (HttpClientErrorException e) {
            if (e.statusCode == HttpStatus.NOT_FOUND) {
                throw new ProductNameNotFoundException(e)
            } else {
                throw e
            }
        }
    }
}