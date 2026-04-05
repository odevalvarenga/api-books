package com.example.books.service;

import java.util.List;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.example.books.repository.AuthorRepository;
import com.example.books.entities.Author;

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