package com.company.books.backend.controller;

import com.company.books.backend.model.Category;
import com.company.books.backend.response.CategoryResponseRest;
import com.company.books.backend.service.CategoryService;
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
public class CategoryRestController {

    @Autowired
    private CategoryService service;

    @GetMapping("/category")
    public ResponseEntity<CategoryResponseRest> getAll() {
        ResponseEntity<CategoryResponseRest> response = service.findCategories();
        return response;
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryResponseRest> getById(@PathVariable Long id) {

        ResponseEntity<CategoryResponseRest> response = service.findById(id);
        return response;
    }

    @PostMapping("/category")
    public ResponseEntity<CategoryResponseRest> create(@RequestBody Category category) {
        ResponseEntity<CategoryResponseRest> reponse = service.create(category);
        return reponse;
    }

    @PutMapping("/category/{id}")
    public ResponseEntity<CategoryResponseRest> update(@RequestBody Category category,
                                                       @PathVariable Long id) {
        ResponseEntity<CategoryResponseRest> reponse = service.update(category, id);
        return reponse;
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<CategoryResponseRest> delete(@PathVariable Long id) {

        ResponseEntity<CategoryResponseRest> reponse = service.delete(id);
        return reponse;
    }
}
