package com.gcu.cst323.controller;

import com.gcu.cst323.service.CategoryService;
import com.gcu.cst323.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Home/dashboard controller - landing page for the application.
 */
@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public String home(Model model) {
        logger.info("Entering HomeController.home()");
        model.addAttribute("productCount", productService.findAll().size());
        model.addAttribute("categoryCount", categoryService.findAll().size());
        model.addAttribute("recentProducts", productService.findAll());
        logger.info("Exiting HomeController.home()");
        return "index";
    }
}
