package com.pairlearning.expensetracker.domain;

public class Car {

    private Integer carId;
    private Integer userId;
    private String title;
    private String description;
    private Double totalExpense;

    public Car(Integer carId, Integer userId, String title, String description, Double totalExpense) {
        this.carId = carId;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.totalExpense = totalExpense;
    }

    public Integer getCarId() { return carId; }

    public void setCarId(Integer carId) { this.carId = carId; }

    public Integer getUserId() { return userId; }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(Double totalExpense) {
        this.totalExpense = totalExpense;
    }
}
