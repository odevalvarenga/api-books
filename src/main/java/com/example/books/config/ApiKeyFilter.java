package com.example.books.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class ApiKeyFilter extends OncePerRequestFilter {

    private static final String API_KEY = "123456";

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)

            throws ServletException, IOException {

        String path = request.getRequestURI();

        // libera arquivos frontend
        if (
                path.equals("/")
                        || path.endsWith(".html")
                        || path.endsWith(".css")
                        || path.endsWith(".js")
                        || path.startsWith("/swagger-ui")
                        || path.startsWith("/v3/api-docs")
        ) {

            filterChain.doFilter(request, response);

            return;
        }

        // libera OPTIONS
        if (request.getMethod().equalsIgnoreCase("OPTIONS")) {

            filterChain.doFilter(request, response);

            return;
        }

        String apiKey =
                request.getHeader("x-api-key");

        if (apiKey == null
                || !apiKey.equals(API_KEY)) {

            response.setStatus(
                    HttpServletResponse.SC_UNAUTHORIZED
            );

            response.setContentType(
                    "application/json"
            );

            response.getWriter().write("""
                    {
                        "erro":"API Key inválida"
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