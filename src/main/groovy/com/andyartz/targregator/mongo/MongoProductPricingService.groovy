package com.andyartz.targregator.mongo

import com.andyartz.targregator.ProductPricingNotFoundException
import com.andyartz.targregator.ProductPricingService
import com.andyartz.targregator.domain.Pricing
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MongoProductPricingService implements ProductPricingService {

    @Autowired
    ProductRepository productRepository

    @Override
    Pricing getProductPricing(Integer integer) {
        productRepository.findById(BigInteger.valueOf(integer)).map {
            new Pricing(currency: Currency.getInstance('USD'), amount: it.currentPriceInUSDollars)
        }.orElseThrow {
            new ProductPricingNotFoundException(null)
        }
    }
}
