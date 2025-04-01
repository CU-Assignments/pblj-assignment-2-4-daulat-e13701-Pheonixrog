CREATE DATABASE ProductDB;

USE ProductDB;

CREATE TABLE Product (
    ProductID INT PRIMARY KEY AUTO_INCREMENT,
    ProductName VARCHAR(100),
    Price DECIMAL(10, 2),
    Quantity INT
);
