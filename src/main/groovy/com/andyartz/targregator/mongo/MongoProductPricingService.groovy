package com.andyartz.targregator.mongo

import com.andyartz.targregator.ProductPricingNotFoundException
import com.andyartz.targregator.ProductPricingService
import com.andyartz.targregator.domain.Pricing
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class MongoProductPricingService implements ProductPricingService {

    @Autowired
    ProductRepository productRepository

    @Qualifier('dollars')
    @Autowired
    Currency currency

    @Override
    Pricing getProductPricing(Integer integer) {
        productRepository.findById(BigInteger.valueOf(integer)).map {
            BigDecimal amount = getAmount(it)
            new Pricing(currency:currency, amount:amount)
        }.orElseThrow {
            new ProductPricingNotFoundException(null)
        }
    }

    private BigDecimal getAmount(ProductRecord it) {
        it.currentPrice.round(currency.defaultFractionDigits)
    }
}
