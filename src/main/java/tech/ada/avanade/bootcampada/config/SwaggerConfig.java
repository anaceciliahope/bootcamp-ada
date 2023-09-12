package tech.ada.avanade.bootcampada.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI usersMicroserviceOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Bootcamp Avanade Ada")
                        .description("Projeto final do bootcamp Avanade, ofertado pela Ada")
                        .version("1.0"));

    }

}