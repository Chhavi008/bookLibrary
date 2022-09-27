package com.example.model;

import com.example.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class BookAuthorResponse {
    private Book book;
    private List<Integer> authorsFailedList = new ArrayList<>();

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public List<Integer> getAuthorsFailedList() {
        return authorsFailedList;
    }

    public void setAuthorsFailedList(List<Integer> authorsFailedList) {
        this.authorsFailedList = authorsFailedList;
    }
}
