CREATE DATABASE StudentDB;

USE StudentDB;

CREATE TABLE Student (
    StudentID INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(100),
    Department VARCHAR(50),
    Marks FLOAT
);
