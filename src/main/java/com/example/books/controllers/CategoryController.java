package com.example.books.controllers;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import com.example.books.entities.Category;
import com.example.books.service.CategoryService;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@Tag(name = "Categories", description = " ")
public class CategoryController {

    private final CategoryService service;

    @GetMapping
    public List<Category> listar() {
        return service.listar();
    }

    @PostMapping
    public Category criar(@RequestBody Category category) {
        return service.salvar(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> atualizar(@PathVariable Long id, @RequestBody Category category) {
        return ResponseEntity.ok(service.atualizar(id, category));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}