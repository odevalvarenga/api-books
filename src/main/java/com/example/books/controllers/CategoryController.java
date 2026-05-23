package com.example.books.controllers;

import com.example.books.entities.Category;
import com.example.books.service.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor

@Tag(
        name = "Categorias",
        description = "Operações relacionadas às categorias dos livros"
)

public class CategoryController {

    private final CategoryService service;

    @Operation(summary = "Listar categorias")
    @GetMapping
    public List<Category> listar() {
        return service.listar();
    }

    @Operation(summary = "Criar uma categoria")
    @PostMapping
    public Category criar(
            @RequestBody Category category
    ) {

        return service.salvar(category);
    }

    @Operation(summary = "Atualizar uma categoria")
    @PutMapping("/{id}")
    public ResponseEntity<Category> atualizar(
            @PathVariable Long id,
            @RequestBody Category category
    ) {

        return ResponseEntity.ok(
                service.atualizar(id, category)
        );
    }

    @Operation(summary = "Excluir uma categoria")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable Long id
    ) {

        service.deletar(id);

        return ResponseEntity.noContent().build();
    }
}