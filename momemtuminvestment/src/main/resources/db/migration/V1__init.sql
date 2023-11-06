-- V1__Initial_Population.sql

-- Insert Investor data
INSERT INTO investor (name, surname, date_of_birth, address, mobile_number, email_address)
VALUES ('John', 'Doe', '1990-01-15', '123 Main St', '123-456-7890', 'john.doe@example.com');

-- Insert Products data
INSERT INTO product (name, type, balance)
VALUES ('Retirement Fund', 'RETIREMENT', 500000),
       ('Savings Account', 'SAVINGS', 36000);
