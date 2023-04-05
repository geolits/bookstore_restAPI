package com.example.bookstore.demobookstore.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BookSearchDto {
    List<String> authorNamesList = new ArrayList<>();
    Integer minNumberOfVolumes;
    List<String> bookGenresList = new ArrayList<>();
    List<String> bookTypesList = new ArrayList<>();
}
