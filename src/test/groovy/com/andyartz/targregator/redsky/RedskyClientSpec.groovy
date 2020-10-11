package com.andyartz.targregator.redsky

import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

class RedskyClientSpec extends Specification {

    RedskyClient systemUnderTest

    RestTemplate mockRestTemplate = Mock()

    void setup() {
        systemUnderTest = new RedskyClient(restTemplate:mockRestTemplate, url:'any string')
    }

    void "should get response"() {
        given:
        ResponseDto mockResponseDto = Mock()
        mockRestTemplate.getForEntity('any string', ResponseDto, 876) >> Mock(ResponseEntity) {
            it.body >> mockResponseDto
        }

        expect:
        systemUnderTest.getProduct(876) == mockResponseDto
    }
}
