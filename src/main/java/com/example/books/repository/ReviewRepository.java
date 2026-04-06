package com.example.books.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.books.entities.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}