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

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    private final PublisherRepository publisherRepository;

    public Page<Book> listar(Pageable pageable) {
        return repository.findAll(pageable);
    }
    public List<Book> buscarPorTitulo(String title) {
        return repository.findByTitleContainingIgnoreCase(title);
    }
    public void deletar(Long id) {
        Book existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        repository.delete(existente);
    }

    public Book salvar(Book book) {

        Author author = authorRepository.findById(book.getAuthor().getId())
                .orElseThrow(() -> new RuntimeException("Autor não encontrado"));

        Category category = categoryRepository.findById(book.getCategory().getId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        Publisher publisher = publisherRepository.findById(book.getPublisher().getId())
                .orElseThrow(() -> new RuntimeException("Editora não encontrada"));

        book.setPublisher(publisher);
        book.setAuthor(author);
        book.setCategory(category);

        return repository.save(book);
    } // ✅ FECHOU AQUI

    public Book atualizar(Long id, Book book) {

        Book existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        Author author = authorRepository.findById(book.getAuthor().getId())
                .orElseThrow(() -> new RuntimeException("Autor não encontrado"));

        Category category = categoryRepository.findById(book.getCategory().getId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        Publisher publisher = publisherRepository.findById(book.getPublisher().getId())
                .orElseThrow(() -> new RuntimeException("Editora não encontrada"));

        existente.setTitle(book.getTitle());
        existente.setAuthor(author);
        existente.setCategory(category);
        existente.setPublisher(publisher);

        return repository.save(existente);
    }

}