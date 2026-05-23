package com.example.books.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class IdempotencyFilter extends OncePerRequestFilter {

    private final Set<String> chavesUsadas =
            ConcurrentHashMap.newKeySet();

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)

            throws ServletException, IOException {

        String path = request.getRequestURI();

        // ignora swagger
        if (path.startsWith("/swagger-ui")
                || path.startsWith("/v3/api-docs")) {

            filterChain.doFilter(request, response);
            return;
        }

        // aplica só em POST
        if (request.getMethod().equals("POST")) {

            String chave =
                    request.getHeader("Idempotency-Key");

            if (chave != null) {

                if (chavesUsadas.contains(chave)) {

                    response.setStatus(409);

                    response.setContentType(
                            "application/json"
                    );

                    response.setCharacterEncoding("UTF-8");

                    ApiError error = new ApiError(
                            409,
                            "Conflict",
                            "Requisição duplicada",
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

                chavesUsadas.add(chave);
            }
        }

        filterChain.doFilter(request, response);
    }
}