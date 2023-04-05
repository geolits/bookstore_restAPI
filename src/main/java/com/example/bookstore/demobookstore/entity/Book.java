package com.example.bookstore.demobookstore.entity;

import com.example.bookstore.demobookstore.enums.BookType;
import com.example.bookstore.demobookstore.enums.Genre;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "title", "volume" }) })
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @Temporal(TemporalType.DATE)
    private Date publicationDate;

    @JsonIgnoreProperties("books")
    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authors;

    @NotNull
    private BookType bookType;

    @NotNull
    private Genre genre;

    @NotNull
    private double price;

    @NotNull
    private int quantity;

    @NotNull
    private int volume;
}
