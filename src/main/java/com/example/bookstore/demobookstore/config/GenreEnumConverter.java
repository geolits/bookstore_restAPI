package com.example.bookstore.demobookstore.config;

import com.example.bookstore.demobookstore.enums.Genre;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class GenreEnumConverter implements AttributeConverter<Genre, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Genre genre) {
        if (genre == null) {
            return null;
        }
        return genre.getGenreCode();
    }

    @Override
    public Genre convertToEntityAttribute(Integer genre) {
        if (genre == null) {
            return null;
        }

        return Stream.of(Genre.values())
                .filter(c -> c.getGenreCode() == genre)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
