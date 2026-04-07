package com.example.books.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.books.entities.Book;
import com.example.books.service.BookService;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService service;

    // PAGINAÇÃO
    @GetMapping
    public Page<Book> listar(Pageable pageable) {
        return service.listar(pageable);
    }

    @PostMapping
    public Book criar(@RequestBody Book book) {
        return service.salvar(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> atualizar(@PathVariable Long id, @RequestBody Book book) {
        Book atualizado = service.atualizar(id, book);
        return ResponseEntity.ok(atualizado);
    }

    @GetMapping("/search")
    public List<Book> buscarPorTitulo(@RequestParam String title) {
        return service.buscarPorTitulo(title);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}