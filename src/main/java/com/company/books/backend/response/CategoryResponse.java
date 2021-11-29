package com.company.books.backend.response;

import com.company.books.backend.model.Category;
import lombok.Data;

import java.util.List;

@Data
public class CategoryResponse {
    private List<Category> categoryList;
}
