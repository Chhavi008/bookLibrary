package com.example.Service;

import com.example.entity.Author;
import com.example.entity.Book;
import com.example.exceptions.AuthorsNotPresentException;
import com.example.exceptions.BookNotPresentException;
import com.example.model.BookAuthorsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.repository.AuthorRepository;
import com.example.repository.BookRepository;

import java.util.*;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public Book addBook(Book book) {
        return bookRepository.save(book);

    }

    public Optional<Book> findByBookId(int id) {
        return bookRepository.findById(id);
    }

    public void deleteBook(int id) {
        bookRepository.deleteById(id);

    }

    public Book updatebookName(Book book) throws BookNotPresentException {
        boolean exists = bookRepository.existsById(book.getId());
        if (!exists) {
            throw new BookNotPresentException("Book is not Present for id=" + book.getId());
        }
        return bookRepository.save(book);
    }

    public Book updateBookAuthorsName(BookAuthorsRequest bookAuthorsRequest) throws BookNotPresentException,AuthorsNotPresentException {

        //checking book is present or not
        boolean bookExists = bookRepository.existsById(bookAuthorsRequest.getBookId());
        if (!bookExists) {
            throw new BookNotPresentException("Book is not Present for id="  + bookAuthorsRequest.getBookId());
        }

        Set<Author> authorsToUpdate = new LinkedHashSet<>();
        List<Integer> authorsFailedList = new ArrayList<>();

        //checking author id is present
        for (int authorId: bookAuthorsRequest.getAuthorIds()) {
            boolean exists = authorRepository.existsById(authorId);
            if (!exists) {
                authorsFailedList.add(authorId);
            }
            else {
                Optional<Author> authorRes = authorRepository.findById(authorId);
                Author author = authorRes.get();
                authorsToUpdate.add(author);
            }
        }

        //setting authorIDs in the book object and save
        Optional<Book> bookRes =bookRepository.findById(bookAuthorsRequest.getBookId());
        Book book = bookRes.get();
        Set<Author> authorsPresent =book.getAuthors();
        authorsPresent.addAll(authorsToUpdate);
        book.setAuthors(authorsPresent);
        bookRepository.save(book);

        if(!authorsFailedList.isEmpty())
        {
            throw new AuthorsNotPresentException("Authors not found for id=" + bookAuthorsRequest.getBookId());
        }

        return bookRepository.save(book);
    }

    public List<Book> findAllBookSorted(String sortedBy) {
        List<Book> bookList=null;
        if(sortedBy.equalsIgnoreCase("author"))
        {
            bookList= bookRepository.findAll(Sort.by(Sort.Direction.DESC, "authors"));
        }
        else if (sortedBy.equalsIgnoreCase("bookname")){
            bookList= bookRepository.findAll(Sort.by(Sort.Direction.DESC, "bookName"));
        }
        return bookList;
    }
}
