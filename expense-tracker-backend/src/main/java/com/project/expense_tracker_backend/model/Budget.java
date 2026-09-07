package com.project.expense_tracker_backend.model;

public class Budget {
    private int id;
    private String category;
    private double amount;

    public Budget() {
    }

    public Budget(String category, double amount) {
        this.category = category;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
