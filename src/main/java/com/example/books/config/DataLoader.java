package com.example.books.config;

import com.example.books.entities.Author;
import com.example.books.entities.Book;
import com.example.books.entities.Category;
import com.example.books.entities.Publisher;

import com.example.books.repository.AuthorRepository;
import com.example.books.repository.BookRepository;
import com.example.books.repository.CategoryRepository;
import com.example.books.repository.PublisherRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    private final CategoryRepository categoryRepository;

    private final PublisherRepository publisherRepository;

    @Override
    public void run(String... args) {

        // evita duplicar dados
        if (bookRepository.count() > 0) {

            return;
        }


        // =========================
        // AUTORES
        // =========================

        Author martin = new Author();
        martin.setName("Robert C. Martin");
        martin.setEmail("martin@email.com");


        Author sapkowski = new Author();
        sapkowski.setName("Andrzej Sapkowski");
        sapkowski.setEmail("sapkowski@email.com");


        Author tolkien = new Author();
        tolkien.setName("J.R.R Tolkien");
        tolkien.setEmail("tolkien@email.com");


        Author gibson = new Author();
        gibson.setName("William Gibson");
        gibson.setEmail("gibson@email.com");


        Author herbert = new Author();
        herbert.setName("Frank Herbert");
        herbert.setEmail("herbert@email.com");


        Author bloch = new Author();
        bloch.setName("Joshua Bloch");
        bloch.setEmail("bloch@email.com");


        authorRepository.save(martin);
        authorRepository.save(sapkowski);
        authorRepository.save(tolkien);
        authorRepository.save(gibson);
        authorRepository.save(herbert);
        authorRepository.save(bloch);



        // =========================
        // CATEGORIAS
        // =========================

        Category fantasy = new Category();
        fantasy.setName("Fantasia");


        Category sciFi = new Category();
        sciFi.setName("Ficção Científica");


        Category programming = new Category();
        programming.setName("Programação");


        categoryRepository.save(fantasy);
        categoryRepository.save(sciFi);
        categoryRepository.save(programming);



        // =========================
        // EDITORAS
        // =========================

        Publisher altaBooks = new Publisher();
        altaBooks.setName("Alta Books");


        Publisher orbit = new Publisher();
        orbit.setName("Orbit");


        Publisher harper = new Publisher();
        harper.setName("Harper Collins");


        publisherRepository.save(altaBooks);
        publisherRepository.save(orbit);
        publisherRepository.save(harper);



        // =========================
        // LIVROS
        // =========================

        Book cleanCode = new Book();
        cleanCode.setTitle("Clean Code");
        cleanCode.setAuthor(martin);
        cleanCode.setCategory(programming);
        cleanCode.setPublisher(altaBooks);


        Book witcher = new Book();
        witcher.setTitle("The Witcher");
        witcher.setAuthor(sapkowski);
        witcher.setCategory(fantasy);
        witcher.setPublisher(orbit);


        Book lotr = new Book();
        lotr.setTitle("Senhor dos Aneis");
        lotr.setAuthor(tolkien);
        lotr.setCategory(fantasy);
        lotr.setPublisher(harper);


        Book dune = new Book();
        dune.setTitle("Duna");
        dune.setAuthor(herbert);
        dune.setCategory(sciFi);
        dune.setPublisher(orbit);


        Book neuromancer = new Book();
        neuromancer.setTitle("Neuromancer");
        neuromancer.setAuthor(gibson);
        neuromancer.setCategory(sciFi);
        neuromancer.setPublisher(harper);


        Book effectiveJava = new Book();
        effectiveJava.setTitle("Java Efetivo");
        effectiveJava.setAuthor(bloch);
        effectiveJava.setCategory(programming);
        effectiveJava.setPublisher(altaBooks);


        bookRepository.save(cleanCode);
        bookRepository.save(witcher);
        bookRepository.save(lotr);
        bookRepository.save(dune);
        bookRepository.save(neuromancer);
        bookRepository.save(effectiveJava);


        System.out.println(
                "Livros geek carregados!"
        );
    }
}