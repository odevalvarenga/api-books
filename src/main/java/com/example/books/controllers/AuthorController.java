package com.example.books.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import com.example.books.entities.Author;
import com.example.books.service.AuthorService;

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
    public Author criar(@RequestBody Author author) {
        return service.salvar(author);
    }
}