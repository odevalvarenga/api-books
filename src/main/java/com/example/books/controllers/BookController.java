package com.example.books.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import jakarta.validation.Valid;

import com.example.books.entities.Book;
import com.example.books.service.BookService;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
@Tag(
        name = "Books",
        description = "Operações relacionadas ao gerenciamento de livros"
)
public class BookController {

    private final BookService service;

    @Operation(summary = "Listar livros com paginação")
    @GetMapping
    public Page<Book> listar(Pageable pageable) {
        return service.listar(pageable);
    }

    @Operation(summary = "Criar um novo livro")
    @PostMapping
    public ResponseEntity<Book> criar(@Valid @RequestBody Book book) {
        Book novo = service.salvar(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(novo);
    }

    @Operation(summary = "Atualizar um livro")
    @PutMapping("/{id}")
    public ResponseEntity<Book> atualizar(@PathVariable Long id, @Valid @RequestBody Book book) {
        Book atualizado = service.atualizar(id, book);
        return ResponseEntity.ok(atualizado);
    }

    @Operation(summary = "Buscar livros por título")
    @GetMapping("/search")
    public List<Book> buscarPorTitulo(@RequestParam String title) {
        return service.buscarPorTitulo(title);
    }

    @Operation(summary = "Deletar um livro")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Book> buscarPorId(@PathVariable Long id) {
        Book book = service.buscarPorId(id);
        return ResponseEntity.ok(book);
    }
    @GetMapping("/author/{authorId}")
    public List<Book> buscarPorAutor(@PathVariable Long authorId) {
        return service.buscarPorAutor(authorId);
    }
}