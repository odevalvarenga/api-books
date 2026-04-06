package com.example.books.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.books.entities.Publisher;
import com.example.books.service.PublisherService;

import java.util.List;

@RestController
@RequestMapping("/publishers")
@RequiredArgsConstructor
public class PublisherController {

    private final PublisherService service;

    @GetMapping
    public List<Publisher> listar() {
        return service.listar();
    }

    @PostMapping
    public Publisher criar(@RequestBody Publisher publisher) {
        return service.salvar(publisher);
    }
}