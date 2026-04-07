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

    public Author atualizar(Long id, Author author) {
        Author existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Autor não encontrado"));

        existente.setName(author.getName());
        existente.setEmail(author.getEmail());

        return repository.save(existente);
    }

    public void deletar(Long id) {
        Author existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Autor não encontrado"));

        repository.delete(existente);
    }
}