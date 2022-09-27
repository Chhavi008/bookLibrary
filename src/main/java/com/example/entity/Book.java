package com.example.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "BOOK")
public class Book {
    @Id
            @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;


    String bookName;

    @ManyToMany
     Set<Author> authors = new LinkedHashSet<>();


    public Book() {
    }

    public Book(Integer id, String bookName, Set<Author> authors, String description) {
        this.id = id;
        this.bookName = bookName;
        this.authors = authors;
        this.description = description;
    }

    @Column(name = "DESCRIPTION")
    String description;

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }




    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
