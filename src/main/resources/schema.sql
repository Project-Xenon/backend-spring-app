-- Drop the tables
DROP TABLE IF EXISTS customers;

-- Create tables
CREATE TABLE customers (
    id SERIAL,
    first_name VARCHAR(255),
    last_name VARCHAR(255)
);

-- Populate data
INSERT INTO customers(first_name, last_name) VALUES ('Jane', 'Doe');
INSERT INTO customers(first_name, last_name) VALUES ('Josh', 'Gruber');
INSERT INTO customers(first_name, last_name) VALUES ('Hans', 'Gruber');
INSERT INTO customers(first_name, last_name) VALUES ('John', 'Doe');
