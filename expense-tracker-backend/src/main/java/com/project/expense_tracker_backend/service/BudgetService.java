package com.project.expense_tracker_backend.service;
import com.project.expense_tracker_backend.model.Budget;
import com.project.expense_tracker_backend.model.BudgetStatus;
import com.project.expense_tracker_backend.repository.BudgetRepository;


import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class BudgetService {
    private final BudgetRepository budgetRepository;

    public BudgetService(
            BudgetRepository budgetRepository) {

        this.budgetRepository = budgetRepository;
    }

    public List<Budget> getAllBudgets() {

        return budgetRepository.getAllBudgets();
    }
    public List<BudgetStatus> getBudgetStatus() {
        return budgetRepository.getBudgetStatus();
    }
}
