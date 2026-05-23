package com.example.books.controllers;

import com.example.books.entities.Author;
import com.example.books.service.AuthorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor

@Tag(
        name = "Autores",
        description = "Operações relacionadas ao gerenciamento de autores"
)

public class AuthorController {

    private final AuthorService service;

    @Operation(summary = "Listar autores")
    @GetMapping
    public List<Author> listar() {
        return service.listar();
    }

    @Operation(summary = "Criar um autor")
    @PostMapping
    public Author criar(
            @RequestBody Author author
    ) {

        return service.salvar(author);
    }

    @Operation(summary = "Atualizar um autor")
    @PutMapping("/{id}")
    public ResponseEntity<Author> atualizar(
            @PathVariable Long id,
            @RequestBody Author author
    ) {

        return ResponseEntity.ok(
                service.atualizar(id, author)
        );
    }

    @Operation(summary = "Excluir um autor")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable Long id
    ) {

        service.deletar(id);

        return ResponseEntity.noContent().build();
    }
}