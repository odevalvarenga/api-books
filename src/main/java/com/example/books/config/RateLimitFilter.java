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

    private long ultimoReset = System.currentTimeMillis();

    private final int LIMITE = 9;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)

            throws ServletException, IOException {

        // RESET A CADA 1 MINUTO
        if (System.currentTimeMillis() - ultimoReset > 60000) {

            contador = 0;

            ultimoReset = System.currentTimeMillis();
        }


        // LIBERA OPTIONS (CORS)
        if (request.getMethod().equalsIgnoreCase("OPTIONS")) {

            filterChain.doFilter(request, response);

            return;
        }


        String path = request.getRequestURI();


        // IGNORA SWAGGER
        if (path.startsWith("/swagger-ui")
                || path.startsWith("/v3/api-docs")) {

            filterChain.doFilter(request, response);

            return;
        }


        contador++;

        System.out.println(
                "Requisição: " + contador
        );


        // LIMITE EXCEDIDO
        if (contador > LIMITE) {

            response.setStatus(429);

            response.setContentType(
                    "application/json"
            );

            response.setCharacterEncoding("UTF-8");

            response.getWriter().write("""
                    {
                        "status":429,
                        "error":"Too Many Requests",
                        "message":"Limite de requisições excedido"
                    }
                    """);

            response.getWriter().flush();

            return;
        }


        filterChain.doFilter(
                request,
                response
        );
    }
}