package com.project.expense_tracker_backend.controller;

import com.project.expense_tracker_backend.model.CategorySpending;
import com.project.expense_tracker_backend.model.Expense;
import com.project.expense_tracker_backend.model.MonthlySpending;
import com.project.expense_tracker_backend.service.ExpenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@CrossOrigin(origins = "*")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public ResponseEntity<String> addExpense(@RequestBody Expense expense) {

        int result = expenseService.addExpense(expense);

        if (result > 0) {
            return ResponseEntity.ok("Expense added successfully!");
        } else {
            return ResponseEntity.internalServerError().body("Failed to add expense!");
        }
    }

    @GetMapping
    public ResponseEntity<List<Expense>> getAllExpenses() {

        List<Expense> expenses =
                expenseService.getAllExpenses();

        return ResponseEntity.ok(expenses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateExpense(
            @PathVariable int id,
            @RequestBody Expense expense) {

        expense.setId(id);

        int result =
                expenseService.updateExpense(expense);

        if (result > 0) {

            return ResponseEntity.ok(
                    "Expense updated successfully!"
            );

        } else {

            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExpense(
            @PathVariable int id) {

        int result =
                expenseService.deleteExpense(id);

        if (result > 0) {

            return ResponseEntity.ok(
                    "Expense deleted successfully!"
            );

        } else {

            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/total")
    public ResponseEntity<Double> getTotalExpenses() {

        return ResponseEntity.ok(
                expenseService.getTotalExpenses()
        );
    }

    @GetMapping("/analytics/category")
    public ResponseEntity<List<CategorySpending>> getCategorySpending() {
        return ResponseEntity.ok(
                expenseService.getCategorySpending()
        );
    }

    @GetMapping("/analytics/monthly")
    public ResponseEntity<List<MonthlySpending>> getMonthlySpending() {
        return ResponseEntity.ok(
                expenseService.getMonthlySpending()
        );
    }
}


