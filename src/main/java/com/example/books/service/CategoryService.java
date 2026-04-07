package com.example.books.service;

import java.util.List;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import com.example.books.repository.CategoryRepository;
import com.example.books.entities.Category;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;

    //LISTAR
    public List<Category> listar() {
        return repository.findAll();
    }

    // SALVAR(POST)
    public Category salvar(Category category) {
        return repository.save(category);
    }

    //ATUALIZAR(PUT)
    public Category atualizar(Long id, Category category) {

        Category existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        existente.setName(category.getName());

        return repository.save(existente);
    }

    //DELETAR(DELETE)
    public void deletar(Long id) {

        Category categoria = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        repository.delete(categoria);
    }
}