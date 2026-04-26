package com.gcu.cst323.repository;

import com.gcu.cst323.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for Category.
 * Provides built-in CRUD methods plus custom queries as needed.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
