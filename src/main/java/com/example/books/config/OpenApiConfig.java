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

                                .title("Books API")

                                .version("1.0.0")

                                .description("""

API REST para gerenciamento de livros e avaliações.

SEGURANÇA:

• Todas as rotas exigem API Key
• Header obrigatório: x-api-key
• Valor para testes: 123456

RATE LIMIT:

• Limite de requisições consecutivas por cliente
• Excesso retorna: HTTP 429 - Too Many Requests

IDEMPOTÊNCIA:

• Requisições POST utilizam Idempotency-Key
• Requisições duplicadas retornam: HTTP 409 - Conflict

STATUS:

• API online em produção
• Backend deployado no Render
• Frontend separado da API

Projeto acadêmico

""")

                                .contact(
                                        new Contact()
                                                .name("Rafael Alvarenga")
                                )

                                .license(
                                        new License()
                                                .name("Uso acadêmico")
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