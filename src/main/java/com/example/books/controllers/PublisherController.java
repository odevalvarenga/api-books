package com.example.books.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.books.entities.Publisher;
import com.example.books.service.PublisherService;

import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/publishers")
@RequiredArgsConstructor
@Tag(name = "Publishers", description = " ")
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

    @PutMapping("/{id}")
    public ResponseEntity<Publisher> atualizar(@PathVariable Long id, @RequestBody Publisher publisher) {
        return ResponseEntity.ok(service.atualizar(id, publisher));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}