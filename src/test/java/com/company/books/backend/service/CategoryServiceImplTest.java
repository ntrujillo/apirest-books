package com.company.books.backend.service;

import com.company.books.backend.model.Category;
import com.company.books.backend.model.dao.CategoryDao;
import com.company.books.backend.response.CategoryResponseRest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CategoryServiceImplTest {

    @InjectMocks
    CategoryServiceImpl service;

    @Mock
    CategoryDao categoryDao;

    List<Category> list = new ArrayList<>();

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        this.loadCategories();
    }

    @Test
    public void findCategoriesTest() {
        when(categoryDao.findAll()).thenReturn(list);
        ResponseEntity<CategoryResponseRest> response = service.findCategories();
        assertEquals(4, response.getBody().getCategoryResponse().getCategoryList().size());
        verify(categoryDao, times(1)).findAll();
    }

    public void loadCategories() {
        Category cat1 = new Category(1l, "Action", "Action books");
        Category cat2 = new Category(2l, "Fiction", "Fiction books");
        Category cat3 = new Category(3l, "Terror", "Terror books");
        Category cat4 = new Category(4l, "Finance", "Finance books");
        list.add(cat1);
        list.add(cat2);
        list.add(cat3);
        list.add(cat4);
    }

}
