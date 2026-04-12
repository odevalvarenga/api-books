package com.example.books.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.CommandLineRunner;

import java.util.List;

import com.example.books.entities.*;
import com.example.books.enums.Rating;
import com.example.books.repository.*;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadData(
            AuthorRepository authorRepo,
            CategoryRepository categoryRepo,
            PublisherRepository publisherRepo,
            BookRepository bookRepo,
            ReviewRepository reviewRepo
    ) {

        return args -> {

            // AUTHORS
            Author a1 = new Author(null, "Robert Martin", "robert@email.com", null);
            Author a2 = new Author(null, "Joshua Bloch", "bloch@email.com", null);

            authorRepo.saveAll(List.of(a1, a2));

            // CATEGORIES
            Category c1 = new Category(null, "Programação", null);
            Category c2 = new Category(null, "Arquitetura", null);

            categoryRepo.saveAll(List.of(c1, c2));

            // PUBLISHERS
            Publisher p1 = new Publisher(null, "Alta Books", null);
            Publisher p2 = new Publisher(null, "Pearson", null);

            publisherRepo.saveAll(List.of(p1, p2));

            // BOOKS
            Book b1 = new Book();
            b1.setTitle("Clean Code");
            b1.setAuthor(a1);
            b1.setCategory(c1);
            b1.setPublisher(p1);

            Book b2 = new Book();
            b2.setTitle("Effective Java");
            b2.setAuthor(a2);
            b2.setCategory(c1);
            b2.setPublisher(p2);

            Book b3 = new Book();
            b3.setTitle("Refactoring");
            b3.setAuthor(a1);
            b3.setCategory(c2);
            b3.setPublisher(p2);

            bookRepo.saveAll(List.of(b1, b2, b3));

            // REVIEWS
            Review r1 = new Review(null, "Excelente livro", b1, Rating.OTIMO);
            Review r2 = new Review(null, "Muito bom", b2, Rating.BOM);

            reviewRepo.saveAll(List.of(r1, r2));
        };
    }
}