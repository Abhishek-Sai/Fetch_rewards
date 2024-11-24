package com.fetchproject.fetch_project.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class Item {
    @NotNull(message = "One of the items is missing the shortDescription field.")
    @NotEmpty(message = "One of the items has no value in shortDescription.")
    private String shortDescription;

    @NotNull(message = "One of the items is missing the price field.")
    @Positive(message = "Price must be greater than zero.")
    private Double price;

    public Item(String shortDescription, double price) {
        this.shortDescription = shortDescription;
        this.price = price;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
