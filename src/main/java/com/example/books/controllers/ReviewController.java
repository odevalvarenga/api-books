package com.example.books.controllers;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import com.example.books.entities.Review;
import com.example.books.service.ReviewService;

import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor

@Tag(
        name = "Avaliações",
        description = "Operações relacionadas às avaliações dos livros"
)

public class ReviewController {

    private final ReviewService service;


    @Operation(summary = "Listar avaliações")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Avaliações listadas"),
            @ApiResponse(responseCode = "401", description = "API Key inválida"),
            @ApiResponse(responseCode = "429", description = "Limite de requisições excedido")
    })
    @GetMapping
    public List<Review> listar() {

        return service.listar();
    }


    @Operation(summary = "Criar uma avaliação")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Avaliação criada"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "409", description = "Avaliação duplicada"),
            @ApiResponse(responseCode = "401", description = "API Key inválida"),
            @ApiResponse(responseCode = "429", description = "Limite de requisições excedido")
    })
    @PostMapping
    public Review criar(@RequestBody Review review) {

        return service.salvar(review);
    }


    @Operation(summary = "Atualizar uma avaliação")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Avaliação atualizada"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Avaliação não encontrada"),
            @ApiResponse(responseCode = "401", description = "API Key inválida"),
            @ApiResponse(responseCode = "429", description = "Limite de requisições excedido")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Review> atualizar(
            @PathVariable Long id,
            @RequestBody Review review
    ) {

        return ResponseEntity.ok(
                service.atualizar(id, review)
        );
    }


    @Operation(summary = "Excluir uma avaliação")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Avaliação excluída"),
            @ApiResponse(responseCode = "404", description = "Avaliação não encontrada"),
            @ApiResponse(responseCode = "401", description = "API Key inválida"),
            @ApiResponse(responseCode = "429", description = "Limite de requisições excedido")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable Long id
    ) {

        service.deletar(id);

        return ResponseEntity.noContent().build();
    }
}