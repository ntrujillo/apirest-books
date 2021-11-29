package com.company.books.backend.model.dao;

import com.company.books.backend.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryDao extends CrudRepository<Category, Long> {
}
