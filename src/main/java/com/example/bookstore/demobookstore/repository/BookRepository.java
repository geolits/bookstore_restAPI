package com.example.bookstore.demobookstore.repository;

import com.example.bookstore.demobookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
