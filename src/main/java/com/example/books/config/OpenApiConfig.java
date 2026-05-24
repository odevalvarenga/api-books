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

API REST para gerenciamento de livros.

🔗 PRODUÇÃO:
https://api-books-bh0c.onrender.com/

FUNCIONALIDADES:

• Cadastro de livros
• Consulta de livros
• Atualização de registros
• Exclusão de registros
• Busca por título
• Busca por autor
• Paginação
• Gerenciamento de avaliações

SEGURANÇA:

• Todas as rotas exigem API Key
• Header obrigatório:
x-api-key

• Valor para testes:
123456

RATE LIMIT:

• Máximo de 5 requisições consecutivas
• Excesso:
HTTP 429 - Too Many Requests

IDEMPOTÊNCIA:

• Requisições POST utilizam:
Idempotency-Key

• Requisições duplicadas:
HTTP 409 - Conflict

TECNOLOGIAS:

• Java 21
• Spring Boot
• Spring Data JPA
• Hibernate
• Maven
• Swagger OpenAPI 3
• H2 Database

STATUS:
Projeto online e deployado no Render.

                                """)

                                .contact(
                                        new Contact()
                                                .name("Rafael Alvarenga")
                                                .email("devalvarenga@gmail.com")
                                                .url("https://github.com/odevalvarenga")
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