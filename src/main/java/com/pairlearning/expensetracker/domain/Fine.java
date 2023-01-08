package com.pairlearning.expensetracker.domain;

public class Fine {

    private Integer fineId;
    private Integer carId;
    private Integer userId;
    private Double finePrice;
    private String note;
    private String fineCategory;
    private Long fineDate;

    public Fine(Integer fineId, Integer carId, Integer userId, Double finePrice, String note, String fineCategory, Long fineDate) {
        this.fineId = fineId;
        this.carId = carId;
        this.userId = userId;
        this.finePrice = finePrice;
        this.note = note;
        this.fineCategory = fineCategory;
        this.fineDate = fineDate;
    }

    public Integer getFineId() {
        return fineId;
    }

    public void setFineId(Integer fineId) {
        this.fineId = fineId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getFinePrice() {
        return finePrice;
    }

    public void setFinePrice(Double finePrice) {
        this.finePrice = finePrice;
    }

    public String getNote() { return note; }

    public void setNote(String note) {
        this.note = note;
    }

    public String getFineCategory() { return fineCategory; }

    public void setFineCategory(String fineCategory) {
        this.fineCategory = fineCategory;
    }

    public Long getFineDate() {
        return fineDate;
    }

    public void setFineDate(Long fineDate) {
        this.fineDate = fineDate;
    }
}
