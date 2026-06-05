-- V1__init.sql
-- Flyway migration for Tenant, User, Provision and their relationships

CREATE TABLE tenants (
    id VARCHAR(255) PRIMARY KEY,
    company_name VARCHAR(255) NOT NULL,
    company_code VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL,
    create_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    admin_username VARCHAR(255) NOT NULL,
    admin_email VARCHAR(255) NOT NULL,
    admin_password VARCHAR(255) NOT NULL,
    admin_first_name VARCHAR(255) NOT NULL,
    admin_last_name VARCHAR(255) NOT NULL
);

CREATE TABLE users (
    id VARCHAR(255) PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255),
    role VARCHAR(255) NOT NULL,
    tenant_id VARCHAR(255),
    create_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_user_tenant FOREIGN KEY (tenant_id) REFERENCES tenants(id)
);

CREATE TABLE provision (
    id VARCHAR(255) PRIMARY KEY,
    tenant_id VARCHAR(255) NOT NULL,
    company_code VARCHAR(255) NOT NULL,
    company_name VARCHAR(255) NOT NULL,
    service_type VARCHAR(255) NOT NULL,
    state VARCHAR(255) NOT NULL,
    create_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

