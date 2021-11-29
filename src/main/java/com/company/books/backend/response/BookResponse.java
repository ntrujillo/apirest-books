package com.company.books.backend.response;

import com.company.books.backend.model.Book;
import lombok.Data;

import java.util.List;

@Data
public class BookResponse {

    private List<Book> book;

}
