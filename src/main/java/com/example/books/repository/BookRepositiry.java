package com.example.books.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public class interface BookRepositiry extends JpaRepository<Book, Long> {

}
