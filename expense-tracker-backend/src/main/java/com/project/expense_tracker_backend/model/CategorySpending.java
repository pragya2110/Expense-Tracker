package com.project.expense_tracker_backend.model;

public class CategorySpending {
    private String category;
    private double totalAmount;

    public CategorySpending() {
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
