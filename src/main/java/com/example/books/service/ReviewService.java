package com.example.books.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.books.entities.Review;
import com.example.books.entities.Book;
import com.example.books.repository.ReviewRepository;
import com.example.books.repository.BookRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository repository;
    private final BookRepository bookRepository;

    public List<Review> listar() {
        return repository.findAll();
    }

    public Review salvar(Review review) {

        Book book = bookRepository.findById(review.getBook().getId())
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        review.setBook(book);

        return repository.save(review);
    }
}