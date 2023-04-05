package com.example.bookstore.demobookstore.config;

import com.example.bookstore.demobookstore.enums.BookType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class BookTypeEnumConverter implements AttributeConverter<BookType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(BookType bookType) {
        if (bookType == null) {
            return null;
        }
        return bookType.getBookType();
    }

    @Override
    public BookType convertToEntityAttribute(Integer bookType) {
        if (bookType == null) {
            return null;
        }

        return Stream.of(BookType.values())
                .filter(c -> c.getBookType() == bookType)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
