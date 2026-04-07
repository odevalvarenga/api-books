package com.example.books.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import lombok.RequiredArgsConstructor;

import com.example.books.entities.Author;
import com.example.books.service.AuthorService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService service;

    @GetMapping
    public List<Author> listar() {
        return service.listar();
    }

    @PostMapping
    public Author criar(@Valid @RequestBody Author author) {
        return service.salvar(author);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> atualizar(@PathVariable Long id, @RequestBody Author author) {
        return ResponseEntity.ok(service.atualizar(id, author));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}