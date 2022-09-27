package com.example.controller;

import com.example.Service.BookService;
import com.example.entity.Book;
import com.example.exceptions.AuthorsNotPresentException;
import com.example.exceptions.BookNotPresentException;
import com.example.model.BookAuthorsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RequestMapping("/bookRequest")
@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/getBook/{id}")
    Optional<Book> findBookById(@PathVariable String id) {
        return bookService.findByBookId(Integer.parseInt(id));
    }

    @GetMapping("/getAllBooksSorted")
    List<Book> findAllBookSorted(@RequestParam(defaultValue = "bookname") String sortedBy) {
        return bookService.findAllBookSorted(sortedBy);
    }

    @PostMapping("/addBook")
    public Book addBook (@RequestBody Book book){
        return bookService.addBook(book);
    }

    @DeleteMapping("/deleteBook/{id}")
    public ResponseEntity deleteBookById(@PathVariable int id) {
         bookService.deleteBook(id);
        return new ResponseEntity("Book deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/updateBookName")
    public Book updateBookName(@RequestBody Book book) throws BookNotPresentException {
        return bookService.updatebookName(book);
    }

    @PutMapping("/updateBookAuthors")
    public Book updateBookAuthorName(@RequestBody BookAuthorsRequest bookAuthorsRequest) throws BookNotPresentException, AuthorsNotPresentException {
       return bookService.updateBookAuthorsName(bookAuthorsRequest);
    }




}
