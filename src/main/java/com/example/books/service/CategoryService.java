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

    public List<Category> listar() {
        return repository.findAll();
    }

    public Category salvar(Category category) {
        return repository.save(category);
    }
}