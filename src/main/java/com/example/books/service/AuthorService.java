package com.example.books.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.books.entities.Author;
import com.example.books.repository.AuthorRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository repository;

    public List<Author> listar() {
        return repository.findAll();
    }

    public Author salvar(Author author) {
        return repository.save(author);
    }
}