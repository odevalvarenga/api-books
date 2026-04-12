package com.example.books.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "Books API",
                version = "v1",
                description = "API REST para gerenciamento de livros, autores, categorias, editoras e avaliações"
        )
)
@Configuration
public class OpenApiConfig {
}