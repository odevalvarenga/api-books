package com.example.books.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.books.entities.Book;
import com.example.books.entities.Author;
import com.example.books.entities.Category;
import com.example.books.entities.Publisher;

import com.example.books.repository.BookRepository;
import com.example.books.repository.AuthorRepository;
import com.example.books.repository.CategoryRepository;
import com.example.books.repository.PublisherRepository;


import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    private final PublisherRepository publisherRepository;

    public List<Book> listar() {
        return repository.findAll();
    }

    public Book salvar(Book book) {

        Author author = authorRepository.findById(book.getAuthor().getId())
                .orElseThrow(() -> new RuntimeException("Ator não emcontrado"));

        Category category = categoryRepository.findById(book.getCategory().getId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        Publisher publisher = publisherRepository.findById(book.getPublisher().getId())
                .orElseThrow(() -> new RuntimeException("Edidora não encontrado"));


        book.setPublisher(publisher);
        book.setAuthor(author);
        book.setCategory(category);

        return repository.save(book);
    }
}