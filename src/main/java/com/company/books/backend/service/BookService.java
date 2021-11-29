package com.company.books.backend.service;

import com.company.books.backend.model.Book;
import com.company.books.backend.response.BookResponseRest;
import org.springframework.http.ResponseEntity;

public interface BookService {
    ResponseEntity<BookResponseRest> getBooks();

    ResponseEntity<BookResponseRest> getBookById(Long id);

    ResponseEntity<BookResponseRest> create(Book book);

    ResponseEntity<BookResponseRest> update(Book book, Long id);

    ResponseEntity<BookResponseRest> delete(Long id);
}
