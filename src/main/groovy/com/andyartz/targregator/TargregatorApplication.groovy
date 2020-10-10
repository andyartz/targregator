package com.andyartz.targregator

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@EnableAspectJAutoProxy
@EnableWebMvc
@SpringBootApplication
class TargregatorApplication {

    static void main(String[] args) {
        SpringApplication.run(TargregatorApplication, args)
    }
}
