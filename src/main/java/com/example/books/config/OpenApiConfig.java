package com.example.books.config;

import io.swagger.v3.oas.models.OpenAPI;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

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

                        .info(
                                new Info()

                                        .title("API de Livros")

                                        .version("1.0")

                                        .description("""

API REST para gerenciamento de livros, autores, categorias, editoras e avaliações.

Esta API permite:

• Cadastro de livros
• Consulta de livros
• Atualização de registros
• Exclusão de registros
• Paginação
• Busca por título
• Busca por autor
• Gerenciamento de avaliações

REGRAS DE SEGURANÇA:

• Todas as rotas exigem API Key
• Header obrigatório: x-api-key
• Valor padrão para testes: 123456

RATE LIMIT:

• Máximo de 5 requisições consecutivas
• Ao exceder o limite:
HTTP 429 - Too Many Requests

IDEMPOTÊNCIA:

• Requisições POST exigem:
Idempotency-Key

• Requisições duplicadas retornam:
HTTP 409 - Conflict

PADRÃO DE ERROS:

Todos os erros retornam:

{
  "timestamp": "...",
  "status": 400,
  "error": "Bad Request",
  "message": "...",
  "path": "..."
}

TECNOLOGIAS UTILIZADAS:

• Java 21
• Spring Boot
• Spring Data JPA
• Hibernate
• Maven
• Swagger OpenAPI 3
• H2 Database

                                """)

                                        .contact(
                                                new Contact()
                                                        .name("Rafael Alvarenga")
                                                        .email("devalvarenga@gmail.com")
                                        )

                                        .license(
                                                new License()
                                                        .name("Projeto acadêmico")
                                        )
                        )

                        .addSecurityItem(
                                new SecurityRequirement()
                                        .addList(securitySchemeName)
                        )

                        .schemaRequirement(
                                securitySchemeName,

                                new SecurityScheme()
                                        .name("x-api-key")
                                        .type(SecurityScheme.Type.APIKEY)
                                        .in(SecurityScheme.In.HEADER)
                        );
        }
}