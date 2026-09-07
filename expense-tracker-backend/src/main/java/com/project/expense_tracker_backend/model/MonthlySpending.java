package com.project.expense_tracker_backend.model;

public class MonthlySpending {
    private String month;
    private double totalAmount;

    public MonthlySpending() {
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
