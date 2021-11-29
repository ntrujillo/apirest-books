package com.company.books.backend.controller;

import com.company.books.backend.model.Book;
import com.company.books.backend.response.BookResponseRest;
import com.company.books.backend.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class BookRestController {

    @Autowired
    private BookService service;

    @GetMapping("/book")
    public ResponseEntity<BookResponseRest> getAll() {
        return service.getBooks();
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<BookResponseRest> getById(@PathVariable Long id) {
        return service.getBookById(id);
    }

    @PostMapping("/book")
    public ResponseEntity<BookResponseRest> create(@RequestBody Book book) {
        return service.create(book);
    }

    @PutMapping("/book/{id}")
    public ResponseEntity<BookResponseRest> update(@RequestBody Book book,
                                                   @PathVariable Long id) {
        return service.update(book, id);
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity<BookResponseRest> create(@PathVariable Long id) {
        return service.delete(id);
    }
}
