package com.andyartz.targregator

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@ActiveProfiles('integrationTest')
@SpringBootTest
class TargregatorApplicationSpec extends Specification {

    void contextLoads() {
        expect:
        true
    }
}
