package com.company.books.backend.model.dao;

import com.company.books.backend.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookDao extends CrudRepository<Book, Long> {

}
