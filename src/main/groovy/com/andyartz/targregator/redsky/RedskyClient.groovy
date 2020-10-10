package com.andyartz.targregator.redsky

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import io.github.resilience4j.bulkhead.annotation.Bulkhead
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import io.github.resilience4j.retry.annotation.Retry
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@ConfigurationProperties('targregator.service.redsky')
@Service
class RedskyClient {

    @Autowired
    RestTemplate restTemplate

    String url

    @Bulkhead(name = 'redsky')
    @CircuitBreaker(name = 'redsky-product')
    @Retry(name = 'redsky-product')
    ResponseDto getProduct(Integer id) {
        restTemplate.getForEntity(url, ResponseDto, id).body
    }
}

@EqualsAndHashCode
@ToString(includePackage = false, includeNames = true)
class ResponseDto {
    ProductDto product
}

@EqualsAndHashCode
@ToString(includePackage = false, includeNames = true)
class ProductDto {
    ItemDto item
}

@EqualsAndHashCode
@ToString(includePackage = false, includeNames = true)
class ItemDto {
    ProductDescriptionDto productDescription
}

@EqualsAndHashCode
@ToString(includePackage = false, includeNames = true)
class ProductDescriptionDto {
    String title
}
