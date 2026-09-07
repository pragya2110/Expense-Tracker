package com.project.expense_tracker_backend.controller;

import com.project.expense_tracker_backend.model.Income;
import com.project.expense_tracker_backend.service.IncomeService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/api/income")
@CrossOrigin(origins = "*")
public class IncomeController {

    private final IncomeService incomeService;

    public IncomeController(
            IncomeService incomeService) {

        this.incomeService = incomeService;
    }

    @GetMapping
    public ResponseEntity<List<Income>> getAllIncome() {

        return ResponseEntity.ok(
                incomeService.getAllIncome()
        );
    }

    @GetMapping("/total")
    public ResponseEntity<Double> getTotalIncome() {

        return ResponseEntity.ok(
                incomeService.getTotalIncome()
        );
    }
}
