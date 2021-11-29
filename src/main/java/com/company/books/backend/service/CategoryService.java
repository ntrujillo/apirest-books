package com.company.books.backend.service;

import com.company.books.backend.model.Category;
import com.company.books.backend.response.CategoryResponseRest;
import org.springframework.http.ResponseEntity;

public interface CategoryService {

    ResponseEntity<CategoryResponseRest> findCategories();

    ResponseEntity<CategoryResponseRest> findById(Long id);

    ResponseEntity<CategoryResponseRest> create(Category category);

    ResponseEntity<CategoryResponseRest> update(Category category, Long id);

    ResponseEntity<CategoryResponseRest> delete(Long id);
}
