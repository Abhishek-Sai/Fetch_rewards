package com.fetchproject.fetch_project.model;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public class Receipt {
    private String id;

    @NotNull(message = "There is no retailer on the receipt.")
    @NotEmpty(message = "The retailer name cannot be empty.")
    private String retailer;
    @NotNull(message = "There is no purchaseDate on the receipt.")
    @NotEmpty(message = "The purchaseDate name cannot be empty.")
    private String purchaseDate;
    @NotNull(message = "There is no purchaseTime on the receipt.")
    @NotEmpty(message = "The purchaseTime name cannot be empty.")
    private String purchaseTime;
    @NotNull(message = "There is no total on the receipt.")
    private Double total;
    @NotNull(message = "There are no items on the receipt.")
    @NotEmpty(message = "There are no items on the receipt.")
    private List<Item> items;
    private int points;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRetailer() {
        return retailer;
    }

    public void setRetailer(String retailer) {
        this.retailer = retailer;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(String purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
