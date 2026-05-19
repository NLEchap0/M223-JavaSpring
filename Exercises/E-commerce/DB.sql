CREATE DATABASE IF NOT EXISTS ecommerce_shoes;
USE ecommerce_shoes;

-- 1. BRANDS
CREATE TABLE brands (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    slug VARCHAR(100) NOT NULL UNIQUE
);

-- 2. CATEGORIES
CREATE TABLE categories (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    slug VARCHAR(100) NOT NULL UNIQUE
);

-- 3. PRODUCTS
CREATE TABLE products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    brand_id INT,
    category_id INT,
    name VARCHAR(150) NOT NULL,
    slug VARCHAR(150) NOT NULL UNIQUE,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (brand_id) REFERENCES brands(id) ON DELETE SET NULL,
    FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE SET NULL
);

-- 4. VARIANTS
CREATE TABLE variants (
    id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT NOT NULL,
    sku VARCHAR(50) NOT NULL UNIQUE,
    color VARCHAR(50) NOT NULL,
    size DECIMAL(3, 1) NOT NULL,
    stock INT NOT NULL DEFAULT 0,
    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
);

-- 5. IMAGES
CREATE TABLE images (
    id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT NOT NULL,
    url VARCHAR(255) NOT NULL,
    cover BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
);

-- 6. USERS (Aggiornata con username e surname)
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE, -- Nome utente univoco per il login
    name VARCHAR(100) NOT NULL,
    surname VARCHAR(100) NOT NULL,        -- Cognome dell'utente
    email VARCHAR(150) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    admin BOOLEAN DEFAULT FALSE
);

-- 7. ORDERS
CREATE TABLE orders (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status ENUM('pending', 'paid', 'shipped', 'delivered', 'canceled') DEFAULT 'pending',
    total DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE SET NULL
);

-- 8. ITEMS
CREATE TABLE items (
    id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT NOT NULL,
    variant_id INT NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
    FOREIGN KEY (variant_id) REFERENCES variants(id)
);

INSERT INTO users (username, name, surname, email, password, admin) 
VALUES (
    'admin', 
    'admin', 
    'admin', 
    'admin@email.com', 
    '$2a$12$VuJP62oB9AI3FwXRUGWj5.3ss/UoFe5nLeo2OwXq4wtDd1t6LMbzS', 
    TRUE
);

INSERT INTO users (username, name, surname, email, password, admin) 
VALUES (
    'user', 
    'user', 
    'user', 
    'user@email.com', 
    '$2a$12$VuJP62oB9AI3FwXRUGWj5.3ss/UoFe5nLeo2OwXq4wtDd1t6LMbzS', 
    FALSE
);