package com.example.books.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.books.entities.Publisher;
import com.example.books.repository.PublisherRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PublisherService {

    private final PublisherRepository repository;

    //LISTAR
    public List<Publisher> listar() {
        return repository.findAll();
    }

    //SALVAR(POST)
    public Publisher salvar(Publisher publisher) {
        return repository.save(publisher);
    }

    // ATUALIZAR(PUT)
    public Publisher atualizar(Long id, Publisher publisher) {

        Publisher existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Editora não encontrada"));

        existente.setName(publisher.getName());

        return repository.save(existente);
    }

    // DELETAR DELETE)
    public void deletar(Long id) {

        Publisher existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Editora não encontrada"));

        repository.delete(existente);
    }
}