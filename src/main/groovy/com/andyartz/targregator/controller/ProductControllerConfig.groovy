package com.andyartz.targregator.controller

import org.modelmapper.ModelMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ProductControllerConfig {

    @Bean
    ModelMapper getModelMapper() {
        new ModelMapper()
    }
}
