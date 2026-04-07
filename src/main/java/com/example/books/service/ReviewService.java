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

    //LISTAR
    public List<Review> listar() {
        return repository.findAll();
    }

    //SALVAR(POST)
    public Review salvar(Review review) {

        Book book = bookRepository.findById(review.getBook().getId())
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        review.setBook(book);

        return repository.save(review);
    }

    //ATUALIZAR(PUT)
    public Review atualizar(Long id, Review review) {

        Review existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review não encontrada"));

        Book book = bookRepository.findById(review.getBook().getId())
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        existente.setComment(review.getComment());
        existente.setRating(review.getRating());
        existente.setBook(book);

        return repository.save(existente);
    }

    //DELETAR(DELETE)
    public void deletar(Long id) {

        Review review = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review não encontrada"));

        repository.delete(review);
    }
}