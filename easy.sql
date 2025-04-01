-- Create the database
CREATE DATABASE IF NOT EXISTS employee_db;
USE employee_db;

-- Create the Employee table
CREATE TABLE IF NOT EXISTS Employee (
    EmpID INT PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
    Salary DECIMAL(10,2) NOT NULL
);

-- Insert sample data
INSERT INTO Employee (EmpID, Name, Salary) VALUES
(1, 'John Doe', 50000.00),
(2, 'Jane Smith', 60000.00),
(3, 'Bob Johnson', 55000.00); 
