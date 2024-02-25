package com.jpcchaves.parkinglotapi.config.springdoc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocOpenApiConfig {

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
                .info(
                        new Info()
                                .title("REST API - Spring Parkinglot")
                                .description("API para gerenciar um estacionamento de veiculos")
                                .version("v1")
                                .license(new License().name("Apache 2.0"))
                                .contact(new Contact().name("jpcchaves").email("jpcchaves@outlook.com"))
                );
    }

}
