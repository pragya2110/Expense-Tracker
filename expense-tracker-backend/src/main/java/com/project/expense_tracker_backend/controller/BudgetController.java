package com.project.expense_tracker_backend.controller;
import com.project.expense_tracker_backend.model.Budget;
import com.project.expense_tracker_backend.model.BudgetStatus;
import com.project.expense_tracker_backend.service.BudgetService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/budgets")
@CrossOrigin(origins = "*")

public class BudgetController {
    private final BudgetService budgetService;

    public BudgetController(
            BudgetService budgetService) {

        this.budgetService = budgetService;
    }

    @GetMapping
    public ResponseEntity<List<Budget>> getAllBudgets() {

        return ResponseEntity.ok(
                budgetService.getAllBudgets()
        );
    }

    @GetMapping("/status")
    public ResponseEntity<List<BudgetStatus>> getBudgetStatus() {
        return ResponseEntity.ok(budgetService.getBudgetStatus());
    }
}
