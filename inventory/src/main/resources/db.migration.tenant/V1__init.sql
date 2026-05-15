-- V1__init.sql
-- Flyway migration for Warehouse and Item entities in inventory module

CREATE TABLE warehouses (
    id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    create_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

CREATE TABLE items (
    id VARCHAR(255) PRIMARY KEY,
    sku VARCHAR(255) NOT NULL,
    product_id VARCHAR(255) NOT NULL,
    quantity_available INTEGER NOT NULL DEFAULT 0,
    quantity_reserved INTEGER NOT NULL DEFAULT 0,
    quantity_sold INTEGER NOT NULL DEFAULT 0,
    warehouse_id VARCHAR(255),
    create_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_item_warehouse FOREIGN KEY (warehouse_id) REFERENCES warehouses(id)
);
