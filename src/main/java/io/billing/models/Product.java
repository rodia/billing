package io.billing.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {
    private int id;
    private String description;
    private double unitPrice;
    private int stock;

    public Product() {
    }

    public Product(int id, String description, double unitPrice, int stock) {
        this.id = id;
        this.description = description;
        this.unitPrice = unitPrice;
        this.stock = stock;
    }

    @JsonProperty
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty
    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @JsonProperty
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
