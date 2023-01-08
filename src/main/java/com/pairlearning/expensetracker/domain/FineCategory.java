package com.pairlearning.expensetracker.domain;

import org.springframework.data.annotation.Id;

public class FineCategory {

    @Id
    private String id;
    private Integer userId;
    private String title;
    private String description;

    public FineCategory(String id, Integer userId, String title, String description) {
        super();
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.description = description;
    }

    public String getFineCategoryId() { return id; }

    public void setFineCategoryId(String carId) { this.id = carId; }

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
}
