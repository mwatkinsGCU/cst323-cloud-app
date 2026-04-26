package com.gcu.cst323.controller;

import com.gcu.cst323.model.Product;
import com.gcu.cst323.service.CategoryService;
import com.gcu.cst323.service.ProductService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * Handles all HTTP requests for Product CRUD operations.
 * Maps to /products/* URLs.
 */
@Controller
@RequestMapping("/products")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    // READ - list all products
    @GetMapping
    public String listProducts(Model model) {
        logger.info("Entering ProductController.listProducts()");
        model.addAttribute("products", productService.findAll());
        logger.info("Exiting ProductController.listProducts()");
        return "product-list";
    }

    // CREATE - show add form
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        logger.info("Entering ProductController.showCreateForm()");
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("formTitle", "Add New Product");
        logger.info("Exiting ProductController.showCreateForm()");
        return "product-form";
    }

    // CREATE - handle form submit
    @PostMapping
    public String createProduct(@Valid @ModelAttribute("product") Product product,
                                BindingResult result, Model model) {
        logger.info("Entering ProductController.createProduct() - name={}", product.getName());
        if (result.hasErrors()) {
            logger.warn("Validation errors on createProduct: {}", result.getAllErrors());
            model.addAttribute("categories", categoryService.findAll());
            model.addAttribute("formTitle", "Add New Product");
            return "product-form";
        }
        productService.save(product);
        logger.info("Exiting ProductController.createProduct()");
        return "redirect:/products";
    }

    // UPDATE - show edit form
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        logger.info("Entering ProductController.showEditForm() - id={}", id);
        Product product = productService.findById(id)
                .orElseThrow(() -> {
                    logger.error("Product not found - id={}", id);
                    return new IllegalArgumentException("Invalid product ID: " + id);
                });
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("formTitle", "Edit Product");
        logger.info("Exiting ProductController.showEditForm()");
        return "product-form";
    }

    // UPDATE - handle form submit
    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable Long id,
                                @Valid @ModelAttribute("product") Product product,
                                BindingResult result, Model model) {
        logger.info("Entering ProductController.updateProduct() - id={}", id);
        if (result.hasErrors()) {
            logger.warn("Validation errors on updateProduct: {}", result.getAllErrors());
            model.addAttribute("categories", categoryService.findAll());
            model.addAttribute("formTitle", "Edit Product");
            return "product-form";
        }
        product.setId(id);
        productService.save(product);
        logger.info("Exiting ProductController.updateProduct()");
        return "redirect:/products";
    }

    // DELETE
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        logger.info("Entering ProductController.deleteProduct() - id={}", id);
        productService.deleteById(id);
        logger.info("Exiting ProductController.deleteProduct()");
        return "redirect:/products";
    }
}
