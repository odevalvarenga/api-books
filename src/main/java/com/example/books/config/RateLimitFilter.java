package com.example.books.config;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class RateLimitFilter extends OncePerRequestFilter {

    private int contador = 0;
    private final int LIMITE = 9;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)

            throws ServletException, IOException {

        String path = request.getRequestURI();

        // libera swagger
        if(path.startsWith("/swagger-ui")
                || path.startsWith("/v3/api-docs")
                || path.startsWith("/swagger-resources")
                || path.startsWith("/webjars")) {

            filterChain.doFilter(request,response);
            return;
        }

        contador++;

        System.out.println(
                "Requisição: " + contador
        );

        if(contador > LIMITE){

            contador = 0;

            response.reset();

            response.setStatus(429);

            response.setContentType("application/json");

            response.getWriter().write(
                    "{\"erro\":\"Limite de requisições excedido\"}"
            );

            return;
        }

        filterChain.doFilter(request,response);
    }
}