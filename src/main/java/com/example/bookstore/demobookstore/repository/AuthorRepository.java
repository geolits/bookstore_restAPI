package com.example.bookstore.demobookstore.repository;

import com.example.bookstore.demobookstore.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
