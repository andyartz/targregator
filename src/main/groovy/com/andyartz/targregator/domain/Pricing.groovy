package com.andyartz.targregator.domain

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode
@ToString(includePackage = false, includeNames = true)
class Pricing {

    BigDecimal amount
    Currency currency
}
