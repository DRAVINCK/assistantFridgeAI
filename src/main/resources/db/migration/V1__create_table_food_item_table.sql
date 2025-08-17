-- V1__create_food_item_table.sql

CREATE TABLE food_item (
       id UUID PRIMARY KEY,
       name VARCHAR(255) NOT NULL,
       category VARCHAR(50) NOT NULL,
       quantity INTEGER NOT NULL,
       expiration_date DATE NOT NULL,
       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);