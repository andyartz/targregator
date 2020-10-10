package com.andyartz.targregator

import com.andyartz.targregator.redsky.RedskyProductNameService
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class RedskyProductNameServiceSpec extends Specification {

    @Autowired
    RedskyProductNameService myRetailProductNameService

    @SpringBean
    ProductPricingService mockMyRetailProductNameService = Mock()

    void "should get a product"() {
        expect:
        myRetailProductNameService.getProductName(13860428) == "The Big Lebowski (Blu-ray)"
    }
}
