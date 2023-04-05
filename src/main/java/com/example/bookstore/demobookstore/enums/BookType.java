package com.example.bookstore.demobookstore.enums;

public enum BookType {
    eBook (1),
    paperback (2),
    hardcover(3);

    private Integer bookType;

    private BookType(Integer code) {
        this.bookType = code;
    }

    public Integer getBookType() {
        return bookType;
    }
}
