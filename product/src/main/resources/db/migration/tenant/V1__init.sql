-- V1__init.sql
-- Flyway migration for Product, Variant, Attribute, Category and their relationships

CREATE TABLE categories (
    id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    create_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

CREATE TABLE products (
    id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    price DOUBLE PRECISION NOT NULL,
    category_id VARCHAR(255),
    create_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_product_category FOREIGN KEY (category_id) REFERENCES categories(id)
);

CREATE TABLE variants (
    id VARCHAR(255) PRIMARY KEY,
    sku VARCHAR(255) NOT NULL UNIQUE,
    product_id VARCHAR(255) NOT NULL,
    create_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_variant_product FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE attributes (
    id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    value VARCHAR(255) NOT NULL,
    variant_id VARCHAR(255),
    create_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_attribute_variant FOREIGN KEY (variant_id) REFERENCES variants(id)
);

