package com.example.books.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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

        // libera preflight CORS
        if (request.getMethod().equalsIgnoreCase("OPTIONS")) {

            filterChain.doFilter(request, response);

            return;
        }

        String path = request.getRequestURI();

        // ignora swagger
        if (path.startsWith("/swagger-ui")
                || path.startsWith("/v3/api-docs")) {

            filterChain.doFilter(request, response);

            return;
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

            response.setCharacterEncoding("UTF-8");

            ApiError error = new ApiError(
                    429,
                    "Too Many Requests",
                    "Limite de requisições excedido",
                    request.getRequestURI()
            );

            response.getWriter().write("""
                    {
                        "timestamp":"%s",
                        "status":%d,
                        "error":"%s",
                        "message":"%s",
                        "path":"%s"
                    }
                    """.formatted(
                    error.getTimestamp(),
                    error.getStatus(),
                    error.getError(),
                    error.getMessage(),
                    error.getPath()
            ));

            response.getWriter().flush();

            return;
        }

        filterChain.doFilter(
                request,
                response
        );
    }
}