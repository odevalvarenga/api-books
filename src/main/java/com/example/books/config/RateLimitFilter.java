package com.example.books.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class RateLimitFilter extends OncePerRequestFilter {

    private int contador = 0;

    private LocalDateTime ultimoReset =
            LocalDateTime.now();

    private final int LIMITE = 9;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)

            throws ServletException, IOException {

        if (request.getMethod()
                .equalsIgnoreCase("OPTIONS")) {

            filterChain.doFilter(request, response);

            return;
        }

        String path = request.getRequestURI();

        if (path.startsWith("/swagger-ui")
                || path.startsWith("/v3/api-docs")) {

            filterChain.doFilter(request, response);

            return;
        }


        // RESET A CADA 1 MINUTO

        if (ultimoReset.plusMinutes(1)
                .isBefore(LocalDateTime.now())) {

            contador = 0;

            ultimoReset = LocalDateTime.now();
        }


        contador++;

        System.out.println(
                "Requisição: " + contador
        );


        if (contador > LIMITE) {

            response.setStatus(429);

            response.setContentType(
                    "application/json"
            );

            response.getWriter().write("""
                    {
                       "status":429,
                       "erro":"Muitas solicitações",
                       "message":"Limite de requisições excedido"
                    }
                    """);

            return;
        }

        filterChain.doFilter(
                request,
                response
        );
    }
}