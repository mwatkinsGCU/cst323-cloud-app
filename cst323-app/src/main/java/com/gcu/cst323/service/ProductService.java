package com.gcu.cst323.service;

import com.gcu.cst323.model.Product;
import com.gcu.cst323.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Business logic layer for Product operations.
 */
@Service
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        logger.info("Entering ProductService.findAll()");
        List<Product> products = productRepository.findAll();
        logger.info("Exiting ProductService.findAll() - returned {} products", products.size());
        return products;
    }

    public Optional<Product> findById(Long id) {
        logger.info("Entering ProductService.findById() - id={}", id);
        Optional<Product> result = productRepository.findById(id);
        logger.info("Exiting ProductService.findById()");
        return result;
    }

    public Product save(Product product) {
        logger.info("Entering ProductService.save() - name={}", product.getName());
        Product saved = productRepository.save(product);
        logger.info("Exiting ProductService.save() - id={}", saved.getId());
        return saved;
    }

    public void deleteById(Long id) {
        logger.info("Entering ProductService.deleteById() - id={}", id);
        productRepository.deleteById(id);
        logger.info("Exiting ProductService.deleteById()");
    }
}
