package com.andyartz.targregator.mongo

import org.springframework.data.annotation.Id

class ProductRecord {

    @Id
    BigInteger id

    String schemaVersion
    BigDecimal currentPrice // this schema version assumes US Dollars, a future version might not

    ProductRecord() {
        schemaVersion = '1.0.0'
    }
}
