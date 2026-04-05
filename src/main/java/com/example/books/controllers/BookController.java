package com.example.books.controllers;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import com.example.books.entities.Book;
import com.example.books.service.BookService;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService service;

    @GetMapping
    public List<Book> listar() {
        return service.listar();
    }

    @PostMapping
    public Book criar(@RequestBody Book book) {
        return service.salvar(book);
    }
}