package com.example.books.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

        @Bean
        public OpenAPI customOpenAPI() {

                final String securitySchemeName = "API Key";

                return new OpenAPI()

                        .info(new Info()
                                .title("Books API")
                                .version("v1")
                                .description("API REST para gerenciamento de livros, autores, categorias, editoras e avaliações"))

                        .addSecurityItem(
                                new SecurityRequirement()
                                        .addList(securitySchemeName)
                        )

                        .components(
                                new Components()
                                        .addSecuritySchemes(
                                                securitySchemeName,

                                                new SecurityScheme()
                                                        .type(SecurityScheme.Type.APIKEY)
                                                        .in(SecurityScheme.In.HEADER)
                                                        .name("x-api-key")
                                        )
                        );
        }
}