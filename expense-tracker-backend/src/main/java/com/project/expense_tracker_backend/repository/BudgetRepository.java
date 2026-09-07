package com.project.expense_tracker_backend.repository;
import com.project.expense_tracker_backend.model.Budget;
import com.project.expense_tracker_backend.model.BudgetStatus;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public class BudgetRepository {
    private final JdbcTemplate jdbcTemplate;

    public BudgetRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Budget> getAllBudgets() {

        String sql = """
                SELECT id, category, amount
                FROM budgets
                ORDER BY id
                """;

        return jdbcTemplate.query(sql, (result, rowNumber) -> {

            Budget budget = new Budget();

            budget.setId(result.getInt("id"));
            budget.setCategory(
                    result.getString("category")
            );
            budget.setAmount(
                    result.getDouble("amount")
            );

            return budget;
        });
    }
    public List<BudgetStatus> getBudgetStatus() {

        String sql = """
            SELECT
                b.id,
                b.category,
                b.amount AS budget_amount,
                COALESCE(SUM(e.amount), 0) AS spent_amount
            FROM budgets b
            LEFT JOIN expenses e
                ON LOWER(TRIM(b.category)) = LOWER(TRIM(e.category))
            GROUP BY b.id, b.category, b.amount
            ORDER BY b.id
            """;

        return jdbcTemplate.query(sql, (result, rowNumber) -> {

            BudgetStatus status = new BudgetStatus();

            double budgetAmount = result.getDouble("budget_amount");
            double spentAmount = result.getDouble("spent_amount");

            status.setId(result.getInt("id"));
            status.setCategory(result.getString("category"));
            status.setBudgetAmount(budgetAmount);
            status.setSpentAmount(spentAmount);

            double remainingAmount = budgetAmount - spentAmount;
            status.setRemainingAmount(remainingAmount);

            double percentageUsed = 0;

            if (budgetAmount > 0) {
                percentageUsed = (spentAmount / budgetAmount) * 100;
            }

            status.setPercentageUsed(percentageUsed);

            return status;
        });
    }
}
