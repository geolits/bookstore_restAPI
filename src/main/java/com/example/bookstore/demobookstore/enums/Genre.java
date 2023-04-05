package com.example.bookstore.demobookstore.enums;

public enum Genre {
    FICTION(1),
    NON_FICTION(2),
    HORROR(3),
    ROMANCE(4),
    SCIENCE_FICTION(5),
    BIOGRAPHY(6),
    HISTORY(7),
    MYSTERY(8),
    FANTASY(9),
    THRILLER(10);

    private Integer genreCode;

    private Genre(Integer code) {
        this.genreCode = code;
    }

    public Integer getGenreCode() {
        return genreCode;
    }
}
