package com.project.expense_tracker_backend.repository;
import java.util.List;

import com.project.expense_tracker_backend.model.CategorySpending;
import com.project.expense_tracker_backend.model.Expense;
import com.project.expense_tracker_backend.model.MonthlySpending;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ExpenseRepository {

    private final JdbcTemplate jdbcTemplate;

    public ExpenseRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int addExpense(Expense expense) {

        String sql = "INSERT INTO expenses (title, amount, category, date) VALUES (?, ?, ?, ?)";

        return jdbcTemplate.update(
                sql,
                expense.getTitle(),
                expense.getAmount(),
                expense.getCategory(),
                expense.getDate()
        );
    }
    public List<Expense> getAllExpenses() {

        String sql = """
            SELECT id, title, amount, category, date
            FROM expenses
            ORDER BY id DESC
            """;

        return jdbcTemplate.query(sql, (result, rowNumber) -> {

            Expense expense = new Expense();

            expense.setId(result.getInt("id"));
            expense.setTitle(result.getString("title"));
            expense.setAmount(result.getDouble("amount"));
            expense.setCategory(result.getString("category"));
            expense.setDate(result.getString("date"));

            return expense;
        });
    }
    public int updateExpense(Expense expense) {

        String sql = """
            UPDATE expenses
            SET title = ?,
                amount = ?,
                category = ?,
                date = ?
            WHERE id = ?
            """;

        return jdbcTemplate.update(
                sql,
                expense.getTitle(),
                expense.getAmount(),
                expense.getCategory(),
                expense.getDate(),
                expense.getId()
        );
    }

    public int deleteExpense(int id) {

        String sql =
                "DELETE FROM expenses WHERE id = ?";

        return jdbcTemplate.update(
                sql,
                id
        );
    }

    public double getTotalExpenses() {

        String sql =
                "SELECT COALESCE(SUM(amount), 0) FROM expenses";

        Double total = jdbcTemplate.queryForObject(
                sql,
                Double.class
        );

        return total != null ? total : 0;
    }

    public List<CategorySpending> getCategorySpending() {

        String sql = """
            SELECT
                category,
                SUM(amount) AS total_amount
            FROM expenses
            GROUP BY category
            ORDER BY total_amount DESC
            """;

        return jdbcTemplate.query(sql, (result, rowNumber) -> {

            CategorySpending spending = new CategorySpending();

            spending.setCategory(
                    result.getString("category")
            );

            spending.setTotalAmount(
                    result.getDouble("total_amount")
            );

            return spending;
        });
    }

    public List<MonthlySpending> getMonthlySpending() {

        String sql = """
            SELECT
                DATE_FORMAT(date, '%Y-%m') AS month,
                SUM(amount) AS total_amount
            FROM expenses
            GROUP BY DATE_FORMAT(date, '%Y-%m')
            ORDER BY month
            """;

        return jdbcTemplate.query(sql, (result, rowNumber) -> {

            MonthlySpending spending = new MonthlySpending();

            spending.setMonth(
                    result.getString("month")
            );

            spending.setTotalAmount(
                    result.getDouble("total_amount")
            );

            return spending;
        });
    }
}