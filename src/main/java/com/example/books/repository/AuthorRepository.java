package com.example.books.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.books.entities.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}