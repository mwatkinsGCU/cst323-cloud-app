package com.gcu.cst323.controller;

import com.gcu.cst323.model.Category;
import com.gcu.cst323.service.CategoryService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * Handles all HTTP requests for Category CRUD operations.
 * Maps to /categories/* URLs.
 */
@Controller
@RequestMapping("/categories")
public class CategoryController {

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    // READ - list all categories
    @GetMapping
    public String listCategories(Model model) {
        logger.info("Entering CategoryController.listCategories()");
        model.addAttribute("categories", categoryService.findAll());
        logger.info("Exiting CategoryController.listCategories()");
        return "category-list";
    }

    // CREATE - show form
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        logger.info("Entering CategoryController.showCreateForm()");
        model.addAttribute("category", new Category());
        model.addAttribute("formTitle", "Add New Category");
        logger.info("Exiting CategoryController.showCreateForm()");
        return "category-form";
    }

    // CREATE - handle submit
    @PostMapping
    public String createCategory(@Valid @ModelAttribute("category") Category category,
                                 BindingResult result, Model model) {
        logger.info("Entering CategoryController.createCategory() - name={}", category.getName());
        if (result.hasErrors()) {
            logger.warn("Validation errors on createCategory: {}", result.getAllErrors());
            model.addAttribute("formTitle", "Add New Category");
            return "category-form";
        }
        categoryService.save(category);
        logger.info("Exiting CategoryController.createCategory()");
        return "redirect:/categories";
    }

    // UPDATE - show form
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        logger.info("Entering CategoryController.showEditForm() - id={}", id);
        Category category = categoryService.findById(id)
                .orElseThrow(() -> {
                    logger.error("Category not found - id={}", id);
                    return new IllegalArgumentException("Invalid category ID: " + id);
                });
        model.addAttribute("category", category);
        model.addAttribute("formTitle", "Edit Category");
        logger.info("Exiting CategoryController.showEditForm()");
        return "category-form";
    }

    // UPDATE - handle submit
    @PostMapping("/update/{id}")
    public String updateCategory(@PathVariable Long id,
                                 @Valid @ModelAttribute("category") Category category,
                                 BindingResult result, Model model) {
        logger.info("Entering CategoryController.updateCategory() - id={}", id);
        if (result.hasErrors()) {
            logger.warn("Validation errors on updateCategory: {}", result.getAllErrors());
            model.addAttribute("formTitle", "Edit Category");
            return "category-form";
        }
        category.setId(id);
        categoryService.save(category);
        logger.info("Exiting CategoryController.updateCategory()");
        return "redirect:/categories";
    }

    // DELETE
    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        logger.info("Entering CategoryController.deleteCategory() - id={}", id);
        categoryService.deleteById(id);
        logger.info("Exiting CategoryController.deleteCategory()");
        return "redirect:/categories";
    }
}
