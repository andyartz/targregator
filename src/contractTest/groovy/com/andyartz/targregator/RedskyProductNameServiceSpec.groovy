package com.andyartz.targregator

import groovy.json.JsonSlurper
import spock.lang.Specification

class RedskyProductNameServiceSpec extends Specification {

    void "service should return JSON with title in correct location"() {
        when:
        Map actual = new JsonSlurper().parse('https://redsky.target.com/v3/pdp/tcin/13860428?key=candidate'.toURL()) as Map

        then:
        actual.product.item.product_description.title == "The Big Lebowski (Blu-ray)"
    }
}
