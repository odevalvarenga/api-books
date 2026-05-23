package com.example.books.controllers;

import com.example.books.entities.Publisher;
import com.example.books.service.PublisherService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publishers")
@RequiredArgsConstructor

@Tag(
        name = "Editoras",
        description = "Operações relacionadas às editoras"
)

public class PublisherController {

    private final PublisherService service;

    @Operation(summary = "Listar editoras")
    @GetMapping
    public List<Publisher> listar() {
        return service.listar();
    }

    @Operation(summary = "Criar uma editora")
    @PostMapping
    public Publisher criar(
            @RequestBody Publisher publisher
    ) {

        return service.salvar(publisher);
    }

    @Operation(summary = "Atualizar uma editora")
    @PutMapping("/{id}")
    public ResponseEntity<Publisher> atualizar(
            @PathVariable Long id,
            @RequestBody Publisher publisher
    ) {

        return ResponseEntity.ok(
                service.atualizar(id, publisher)
        );
    }

    @Operation(summary = "Excluir uma editora")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable Long id
    ) {

        service.deletar(id);

        return ResponseEntity.noContent().build();
    }
}