package com.project.expense_tracker_backend.service;

import com.project.expense_tracker_backend.model.Income;
import com.project.expense_tracker_backend.repository.IncomeRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncomeService {

    private final IncomeRepository incomeRepository;

    public IncomeService(IncomeRepository incomeRepository) {
        this.incomeRepository = incomeRepository;
    }

    public List<Income> getAllIncome() {

        return incomeRepository.getAllIncome();
    }

    public double getTotalIncome() {

        return incomeRepository.getTotalIncome();
    }
}
