package com.example.books.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.example.books.entities.Review;
import com.example.books.service.ReviewService;

import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
@Tag(name = "Reviews", description = " ")
public class ReviewController {

    private final ReviewService service;

    @GetMapping
    public List<Review> listar() {
        return service.listar();
    }

    @PostMapping
    public Review criar(@RequestBody Review review) {
        return service.salvar(review);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Review> atualizar(@PathVariable Long id, @RequestBody Review review) {
        return ResponseEntity.ok(service.atualizar(id, review));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}