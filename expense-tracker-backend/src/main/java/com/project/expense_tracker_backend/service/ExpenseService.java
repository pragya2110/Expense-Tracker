package com.project.expense_tracker_backend.service;

import com.project.expense_tracker_backend.model.CategorySpending;
import com.project.expense_tracker_backend.model.Expense;
import com.project.expense_tracker_backend.model.MonthlySpending;
import com.project.expense_tracker_backend.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public int addExpense(Expense expense) {
        return expenseRepository.addExpense(expense);
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.getAllExpenses();
    }

    public int updateExpense(Expense expense) {
        return expenseRepository.updateExpense(expense);
    }

    public int deleteExpense(int id) {
        return expenseRepository.deleteExpense(id);
    }

    public double getTotalExpenses() {
        return expenseRepository.getTotalExpenses();
    }

    public List<CategorySpending> getCategorySpending() {
        return expenseRepository.getCategorySpending();
    }

    public List<MonthlySpending> getMonthlySpending() {
        return expenseRepository.getMonthlySpending();
    }
}