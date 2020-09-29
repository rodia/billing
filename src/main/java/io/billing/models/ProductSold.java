package io.billing.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductSold extends Product {
    private double total;

    public ProductSold() {
    }

    public ProductSold(int id, String description, double unitPrice, int stock, double total) {
        super(id, description, unitPrice, stock);
        this.total = total;
    }

    @JsonProperty
    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
