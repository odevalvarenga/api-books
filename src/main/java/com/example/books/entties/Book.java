import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

package com.example.books.entties;

@Entity
@Data
@NoArgsConstructor

public class Book {

    @Id
    @GeneratedValue
    private long id;
    private String title;
    private String author;
}
