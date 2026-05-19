package com.example.books.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Component
public class IdempotencyFilter extends OncePerRequestFilter {

    private final Set<String> chavesUsadas = new HashSet<>();

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)

            throws ServletException, IOException {

        // aplica só em POST
        if(request.getMethod().equals("POST")){

            String chave =
                    request.getHeader("Idempotency-Key");

            if(chave != null){

                if(chavesUsadas.contains(chave)){

                    response.setStatus(409);

                    response.setContentType(
                            "application/json"
                    );

                    response.getWriter().write(
                            "{\"erro\":\"Requisição duplicada\"}"
                    );

                    return;
                }

                chavesUsadas.add(chave);
            }
        }

        filterChain.doFilter(request,response);
    }
}