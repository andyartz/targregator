package com.andyartz.targregator.redsky

import com.andyartz.targregator.ProductNameNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.web.client.HttpClientErrorException
import spock.lang.Specification
import spock.lang.Unroll

class RedskyProductNameServiceSpec extends Specification {

    RedskyProductNameService systemUnderTest

    RedskyClient mockRedskyClient = Mock()

    void setup() {
        systemUnderTest = new RedskyProductNameService(redskyClient: mockRedskyClient)
    }

    void "should get product name"() {
        given:
        mockRedskyClient.getProduct(-42) >> Mock(ResponseDto) {
            it.product >> Mock(ProductDto) {
                it.item >> Mock(ItemDto) {
                    it.productDescription >> Mock(ProductDescriptionDto) {
                        it.title >> 'Any Title'
                    }
                }
            }
        }

        expect:
        systemUnderTest.getProductName(-42) == 'Any Title'
    }

    @Unroll
    void "should convert only NOT FOUND http client exceptions into domain exceptions"() {
        given:
        mockRedskyClient.getProduct(345) >> { throw exception }

        when:
        systemUnderTest.getProductName(345)

        then:
        Throwable actual = thrown()

        and:
        actual.class == expectedExceptionClass

        where:
        exception                                            | expectedExceptionClass
        new HttpClientErrorException(HttpStatus.NOT_FOUND)   | ProductNameNotFoundException
        new HttpClientErrorException(HttpStatus.BAD_REQUEST) | HttpClientErrorException
        new HttpClientErrorException(HttpStatus.BAD_GATEWAY) | HttpClientErrorException
        new IOException()                                    | IOException
    }
}
