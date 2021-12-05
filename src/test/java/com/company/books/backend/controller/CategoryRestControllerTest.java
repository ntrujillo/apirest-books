package com.company.books.backend.controller;

import com.company.books.backend.model.Category;
import com.company.books.backend.response.CategoryResponseRest;
import com.company.books.backend.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CategoryRestControllerTest {

    @InjectMocks
    CategoryRestController categoryRestController;

    @Mock
    CategoryService categoryService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void createTest() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Category category = new Category(1l, "Clasics", "Clasic books modern literature");
        when(categoryService
                .create(any(Category.class))).thenReturn(new ResponseEntity<CategoryResponseRest>(HttpStatus.OK));
        ResponseEntity<CategoryResponseRest> response = categoryRestController.create(category);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
