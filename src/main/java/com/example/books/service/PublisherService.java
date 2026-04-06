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

    public List<Publisher> listar() {
        return repository.findAll();
    }

    public Publisher salvar(Publisher publisher) {
        return repository.save(publisher);
    }
}