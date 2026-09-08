-- ============================================
-- EXPENSE TRACKER DATABASE
-- ============================================

-- Create database
CREATE DATABASE expense_tracker;

USE expense_tracker;


-- ============================================
-- 1. EXPENSES TABLE
-- ============================================

CREATE TABLE expenses (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    amount DOUBLE NOT NULL,
    category VARCHAR(100) NOT NULL,
    date DATE NOT NULL
);


-- ============================================
-- 2. INCOME TABLE
-- ============================================

CREATE TABLE income (
    id INT AUTO_INCREMENT PRIMARY KEY,
    source VARCHAR(100) NOT NULL,
    amount DOUBLE NOT NULL,
    date DATE NOT NULL
);


-- ============================================
-- 3. BUDGETS TABLE
-- ============================================

CREATE TABLE budgets (
    id INT AUTO_INCREMENT PRIMARY KEY,
    category VARCHAR(100) NOT NULL,
    amount DOUBLE NOT NULL
);


-- ============================================
-- SAMPLE EXPENSE DATA
-- ============================================

INSERT INTO expenses (title, amount, category, date)
VALUES
('Lunch', 250, 'Food', '2026-09-01'),
('Groceries', 1200, 'Food', '2026-09-01'),
('Metro', 500, 'Travel', '2026-09-02'),
('New Shirt', 1500, 'Shopping', '2026-09-02'),
('Movie', 400, 'Entertainment', '2026-09-03'),
('Dinner', 600, 'Food', '2026-09-03'),
('Cab', 350, 'Travel', '2026-09-03');


-- ============================================
-- SAMPLE INCOME DATA
-- ============================================

INSERT INTO income (source, amount, date)
VALUES
('Salary', 30000, '2026-09-01'),
('Freelancing', 5000, '2026-09-02');


-- ============================================
-- SAMPLE BUDGET DATA
-- ============================================

INSERT INTO budgets (category, amount)
VALUES
('Food', 5000),
('Travel', 3000),
('Shopping', 4000),
('Entertainment', 2000);


-- ============================================
-- CHECK DATA
-- ============================================

SELECT * FROM expenses;

SELECT * FROM income;

SELECT * FROM budgets;

USE expense_tracker;

SELECT category, SUM(amount) AS total
FROM expenses
GROUP BY category;
