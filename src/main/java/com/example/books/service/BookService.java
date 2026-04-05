package com.example.books.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.books.entities.Book;
import com.example.books.repository.BookRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;

    public List<Book> listar() {
        return repository.findAll();
    }

    public Book salvar(Book book) {
        return repository.save(book);
    }
}