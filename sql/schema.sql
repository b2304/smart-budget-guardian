CREATE DATABASE IF NOT EXISTS smart_budget;

USE smart_budget;

CREATE TABLE IF NOT EXISTS expenses (
    id INT AUTO_INCREMENT PRIMARY KEY,
    category VARCHAR(100),
    amount DOUBLE,
    date VARCHAR(20)
);