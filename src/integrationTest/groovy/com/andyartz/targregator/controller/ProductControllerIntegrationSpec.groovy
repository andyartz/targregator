package com.andyartz.targregator.controller

import com.andyartz.targregator.ProductNameNotFoundException
import com.andyartz.targregator.ProductPricingNotFoundException
import com.andyartz.targregator.ProductService
import com.andyartz.targregator.domain.Pricing
import com.andyartz.targregator.domain.Product
import groovy.json.JsonSlurper
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import spock.lang.Specification

@WebMvcTest(controllers = ProductController)
@Import(ProductControllerConfig)
class ProductControllerIntegrationSpec extends Specification {

    @Autowired
    MockMvc mockMvc

    @SpringBean
    ProductService mockProductService = Mock()

    void "should respond to a GET request"() {
        given:
        Pricing pricing = new Pricing(amount:1000.34, currency:Currency.getInstance('USD'))
        Product product = new Product(id:1976, name:'Bubblegum', currentPricing:pricing)
        mockProductService.getProduct(1976) >> product

        expect:
        MvcResult actual = mockMvc.perform(MockMvcRequestBuilders.get('/products/1976'))
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn()

        and:
        new JsonSlurper().parseText(actual.response.getContentAsString()) == new JsonSlurper().parseText('''
            {
                "id":1976,
                "name":"Bubblegum",
                "currentPricing": {
                    "amount":1000.34,
                    "currency":"USD"
                }
            }
        ''')
    }

    void "should return 404 if product name not found"() {
        given:
        mockProductService.getProduct(-2020) >> { throw new ProductNameNotFoundException(null) }

        expect:
        mockMvc.perform(MockMvcRequestBuilders.get('/products/-2020'))
            .andExpect(MockMvcResultMatchers.status().isNotFound())
    }

    void "should return 404 if product pricing not found"() {
        given:
        mockProductService.getProduct(2020) >> { throw new ProductPricingNotFoundException(null) }

        expect:
        mockMvc.perform(MockMvcRequestBuilders.get('/products/2020'))
            .andExpect(MockMvcResultMatchers.status().isNotFound())
    }
}
