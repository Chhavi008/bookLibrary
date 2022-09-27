package com.example.model;

import java.util.List;

public class BookAuthorsRequest {

    private int bookId;
    private List<Integer> authorIds;

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public List<Integer> getAuthorIds() {
        return authorIds;
    }

    public void setAuthorIds(List<Integer> authorIds) {
        this.authorIds = authorIds;
    }
}
