package com.company.books.backend.service;

import com.company.books.backend.model.Category;
import com.company.books.backend.model.dao.CategoryDao;
import com.company.books.backend.response.CategoryResponseRest;
import org.aspectj.weaver.loadtime.definition.LightXMLParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CategoryServiceImpl implements CategoryService {

    private static final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryDao categoryDao;

    @Override
    @Transactional
    public ResponseEntity<CategoryResponseRest> findCategories() {
        log.info("start findCategories");

        CategoryResponseRest response = new CategoryResponseRest();

        try {
            List<Category> categoryList = (List<Category>) categoryDao.findAll();
            response.getCategoryResponse().setCategoryList(categoryList);
            response.setMetadata("OK", "200", "Respuesta Exitosa");
        } catch (Exception e) {
            response.setMetadata("Error", "-1", "Respuesta Incorrecta");
            log.error("Error al consultar categorias", e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoryResponseRest> findById(Long id) {
        log.info("start findById");

        CategoryResponseRest response = new CategoryResponseRest();

        List<Category> categoryList = new ArrayList<>();

        try {
            Optional<Category> category = categoryDao.findById(id);
            if (category.isPresent()) {
                categoryList.add(category.get());
                response.getCategoryResponse().setCategoryList(categoryList);
            } else {
                log.error("Error en consulta de category");
                response.setMetadata("Error", "-1", "Category not found");
                return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            log.error("Error en consulta de category");
            response.setMetadata("Error", "-1", "Category not found");
            return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<CategoryResponseRest> create(Category category) {
        log.info("start create");

        CategoryResponseRest response = new CategoryResponseRest();
        List<Category> categoryList = new ArrayList<>();

        try {
            Category categorySaved = categoryDao.save(category);
            if (categorySaved != null) {
                categoryList.add(categorySaved);
            } else {
                log.error("Error al grabar categoria");
                response.setMetadata("Error", "-1", "Error al grabar categoria");
                return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            log.error("Error al grabar categoria");
            response.setMetadata("Error", "-1", "Error al grabar categoria");
            return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.setMetadata("Respuesta OK", "00", "Categoria creada");
        return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<CategoryResponseRest> update(Category category, Long id) {
        log.info("start update");

        CategoryResponseRest response = new CategoryResponseRest();
        List<Category> categoryList = new ArrayList<>();

        try {
            Optional<Category> categoryFound = categoryDao.findById(id);
            if (categoryFound.isPresent()) {
                categoryFound.get().setName(category.getName());
                categoryFound.get().setDescription(category.getDescription());
                Category categoryUpdated = categoryDao.save(categoryFound.get());
                if (categoryUpdated != null) {
                    response.setMetadata("Respuesta ok", "00", "Category Updated");
                    categoryList.add(categoryUpdated);
                    response.getCategoryResponse().setCategoryList(categoryList);
                } else {
                    log.error("Error en update category");
                    response.setMetadata("Error", "-1", "Category not updated");
                    return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.BAD_REQUEST);
                }
            } else {
                log.error("Error en update category");
                response.setMetadata("Error", "-1", "Category not updated");
                return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Error en update category", e.getMessage());
            e.getStackTrace();
            response.setMetadata("Error", "-1", "Category not updated");
            return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);

    }

    @Override
    @Transactional
    public ResponseEntity<CategoryResponseRest> delete(Long id) {
        log.info("start delete");
        CategoryResponseRest response = new CategoryResponseRest();

        try{
           categoryDao.deleteById(id);
            response.setMetadata("Respuesta ok", "00", "Category Eliminada");
        }catch (Exception e){
            log.error("Error en update category", e.getMessage());
            e.getStackTrace();
            response.setMetadata("Error", "-1", "Category not updated");
            return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
    }
}
