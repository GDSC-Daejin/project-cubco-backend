package config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration    // 스프링 실행시 설정파일 읽기 위한 어노테이션
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo());
    }

    private Info apiInfo() {
        System.out.println("✅ SwaggerConfig loaded");

        return new Info()
                .title("Cubco Swagger")
                .description("Cubco Backend REST API")
                .version("1.0.0");
    }
}