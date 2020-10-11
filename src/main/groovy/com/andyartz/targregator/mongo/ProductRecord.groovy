package com.andyartz.targregator.mongo

import org.springframework.data.annotation.Id

class ProductRecord {

    @Id
    BigInteger id

    String schemaVersion
    BigDecimal currentPriceInUSDollars

    ProductRecord() {
        schemaVersion = '1.0.0'
    }
}
