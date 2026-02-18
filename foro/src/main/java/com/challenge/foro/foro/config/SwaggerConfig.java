package com.challenge.foro.foro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI documentation(){
        return new OpenAPI()
                .info(new Info()
                        .title("Foro API Alura")
                        .version("1.0")
                );
    }

}
