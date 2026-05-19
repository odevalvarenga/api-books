package com.example.books.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título é obrigatório")
    @Size(
            min = 2,
            max = 100,
            message = "Título deve ter entre 2 e 100 caracteres"
    )
    private String title;

    @NotNull(message = "Autor é obrigatório")
    @ManyToOne
    private Author author;

    @NotNull(message = "Categoria é obrigatória")
    @ManyToOne
    private Category category;

    @NotNull(message = "Editora é obrigatória")
    @ManyToOne
    private Publisher publisher;
}