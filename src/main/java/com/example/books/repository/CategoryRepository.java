package com.example.books.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.books.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}