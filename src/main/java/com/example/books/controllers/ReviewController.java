package com.example.books.controllers;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import com.example.books.entities.Review;
import com.example.books.service.ReviewService;

import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

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
    @GetMapping
    public List<Review> listar() {
        return service.listar();
    }

    @Operation(summary = "Criar uma avaliação")
    @PostMapping
    public Review criar(@RequestBody Review review) {
        return service.salvar(review);
    }

    @Operation(summary = "Atualizar uma avaliação")
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
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable Long id
    ) {

        service.deletar(id);

        return ResponseEntity.noContent().build();
    }
}