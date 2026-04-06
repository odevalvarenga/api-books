package com.example.books.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.books.entities.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
}