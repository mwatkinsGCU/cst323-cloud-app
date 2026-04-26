-- =============================================================
-- CST-323 Cloud Test Application - DDL Script
-- Run this script to initialize the MySQL database schema.
-- =============================================================

CREATE DATABASE IF NOT EXISTS cst323db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE cst323db;

-- Drop existing tables (order matters due to FK constraint)
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS categories;

-- -------------------------------------------------------------
-- Table: categories
-- -------------------------------------------------------------
CREATE TABLE categories (
    id          BIGINT       NOT NULL AUTO_INCREMENT,
    name        VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    created_at  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- -------------------------------------------------------------
-- Table: products
-- -------------------------------------------------------------
CREATE TABLE products (
    id          BIGINT         NOT NULL AUTO_INCREMENT,
    category_id BIGINT         NOT NULL,
    name        VARCHAR(150)   NOT NULL,
    description VARCHAR(500),
    quantity    INT            NOT NULL DEFAULT 0,
    price       DECIMAL(10, 2) NOT NULL,
    created_at  DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    CONSTRAINT fk_product_category
        FOREIGN KEY (category_id) REFERENCES categories (id)
        ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- -------------------------------------------------------------
-- Seed data
-- -------------------------------------------------------------
INSERT INTO categories (name, description) VALUES
    ('Electronics', 'Computers, phones, and accessories'),
    ('Office Supplies', 'Pens, paper, staplers, etc.'),
    ('Furniture', 'Desks, chairs, and storage');

INSERT INTO products (category_id, name, description, quantity, price) VALUES
    (1, 'Laptop Pro 15',  'High-performance laptop with 16GB RAM',  12, 1299.99),
    (1, 'Wireless Mouse', 'Ergonomic Bluetooth mouse',               45, 29.99),
    (2, 'Ballpoint Pens', 'Box of 12 blue ballpoint pens',          200, 4.99),
    (2, 'A4 Paper Ream',  '500-sheet ream of 80gsm copy paper',      80, 9.99),
    (3, 'Standing Desk',  'Adjustable height sit-stand desk',         5, 549.00);
