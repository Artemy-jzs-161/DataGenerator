package ru.temaborovik.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Data Generator API") // Название твоего API
                        .version("1.0")              // Версия API
                        .description("API для генерации тестовых данных физических и юридических лиц") // Описание
                        .contact(new Contact()
                                .name("Artem Borovik")
                                .email("artem@example.com")
                                .url("https://temaborovik.ru")) // Контакты
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")) // Лицензия
                );
    }
}
