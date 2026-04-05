package com.example.books.controllers;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import com.example.books.entities.Category;
import com.example.books.service.CategoryService;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
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
}