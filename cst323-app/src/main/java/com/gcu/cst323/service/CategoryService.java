package com.gcu.cst323.service;

import com.gcu.cst323.model.Category;
import com.gcu.cst323.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Business logic layer for Category operations.
 * All CRUD operations are routed through this service.
 */
@Service
public class CategoryService {

    private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll() {
        logger.info("Entering CategoryService.findAll()");
        List<Category> categories = categoryRepository.findAll();
        logger.info("Exiting CategoryService.findAll() - returned {} categories", categories.size());
        return categories;
    }

    public Optional<Category> findById(Long id) {
        logger.info("Entering CategoryService.findById() - id={}", id);
        Optional<Category> result = categoryRepository.findById(id);
        logger.info("Exiting CategoryService.findById()");
        return result;
    }

    public Category save(Category category) {
        logger.info("Entering CategoryService.save() - name={}", category.getName());
        Category saved = categoryRepository.save(category);
        logger.info("Exiting CategoryService.save() - id={}", saved.getId());
        return saved;
    }

    public void deleteById(Long id) {
        logger.info("Entering CategoryService.deleteById() - id={}", id);
        categoryRepository.deleteById(id);
        logger.info("Exiting CategoryService.deleteById()");
    }
}
