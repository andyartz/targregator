package com.andyartz.targregator.mongo

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.Resource
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean

@Configuration
@EnableMongoRepositories
class MongoConfig {

    @Bean
    Jackson2RepositoryPopulatorFactoryBean repositoryInitializer() {
        Resource[] resource = [new ClassPathResource('productPricingData.json')]
        new Jackson2RepositoryPopulatorFactoryBean(resources:resource)
    }

    @Qualifier('dollars')
    @Bean
    Currency getDollarsCurrency() {
        Currency.getInstance('USD')
    }
}
