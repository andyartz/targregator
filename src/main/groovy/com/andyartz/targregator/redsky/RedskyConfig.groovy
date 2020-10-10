package com.andyartz.targregator.redsky

import com.github.benmanes.caffeine.cache.Caffeine
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.caffeine.CaffeineCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

import java.util.concurrent.TimeUnit

@Configuration
@EnableCaching
class RedskyConfig {

    @Bean
    RestTemplate getRedskyRestTemplate(@Autowired RestTemplateBuilder restTemplateBuilder) {
        restTemplateBuilder.build()
    }

    @Bean
    Caffeine getCaffeine(@Value('${targregator.service.redsky.cache.expirationInSeconds}') Integer expirationInSeconds) {
        Caffeine.newBuilder().expireAfterWrite(expirationInSeconds, TimeUnit.SECONDS)
    }

    @Bean
    CacheManager getCacheManager(Caffeine caffeine) {
        new CaffeineCacheManager(cacheNames:['product-names'], caffeine:caffeine, cacheSpecification:'recordStats')
    }
}
