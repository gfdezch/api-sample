package com.gfc.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;


@Configuration
@ComponentScan("com.gfc.api")
public class SampleConfig {

    /**
     * OpenAPI configuration for Swagger.
     * @return OpenAPI object that handles the Swagger configuration.
     */
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
            .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
            .info(new Info().title("Swagger 1.0")
            .description("Sample API project, implementing vertical slicing and hexagonal architecture based on Spring Boot.")
            .version("1.0.0-SNAPSHOT")
            .license(new License().name("MIT").url("https://opensource.org/licenses/MIT")));
    }
}
