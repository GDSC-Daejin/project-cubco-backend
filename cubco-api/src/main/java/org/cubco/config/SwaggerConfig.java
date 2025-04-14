package org.cubco.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo());
    }

    private Info apiInfo() {
        System.out.println("● apiInfo 메소드 실행 ●");
        return new Info()
                .title("Cubco Swagger")
                .description("Cubco Backend REST API")
                .version("1.0.0");
    }
}