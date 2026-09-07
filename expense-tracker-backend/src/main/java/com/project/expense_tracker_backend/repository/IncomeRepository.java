package com.project.expense_tracker_backend.repository;

import com.project.expense_tracker_backend.model.Income;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IncomeRepository {

    private final JdbcTemplate jdbcTemplate;

    public IncomeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Income> getAllIncome() {

        String sql = """
                SELECT id, source, amount, date
                FROM income
                ORDER BY id DESC
                """;

        return jdbcTemplate.query(sql, (result, rowNumber) -> {

            Income income = new Income();

            income.setId(result.getInt("id"));
            income.setSource(result.getString("source"));
            income.setAmount(result.getDouble("amount"));
            income.setDate(result.getString("date"));

            return income;
        });
    }

    public double getTotalIncome() {

        String sql =
                "SELECT COALESCE(SUM(amount), 0) FROM income";

        Double total = jdbcTemplate.queryForObject(
                sql,
                Double.class
        );

        return total != null ? total : 0;
    }
}
