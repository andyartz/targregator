package com.andyartz.targregator

import com.andyartz.targregator.domain.Pricing
import com.andyartz.targregator.domain.Product
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AggregatedProductService implements ProductService {

    @Autowired
    ProductNameService productNameService

    @Autowired
    ProductPricingService productPricingService

    @Override
    Product getProduct(Integer id) {

        String name = productNameService.getProductName(id)
        Pricing pricing = productPricingService.getProductPricing(id)

        new Product(id:id, name:name, currentPricing:pricing)
    }
}


