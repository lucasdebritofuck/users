package br.com.lucasfuck.user.adapter.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfiguration {

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI().info(new Info()
                .title("User CRUD")
                .contact(new Contact().email("lucasdebritof1@gmail.com")));
    }

    @Bean
    public GroupedOpenApi groupedOpenApi() {
        return GroupedOpenApi.builder()
                .group("user")
                .pathsToMatch("/**")
                .build();
    }
}
