package com.example.books.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;

    private int rating; // nota de 1 a 5

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
}