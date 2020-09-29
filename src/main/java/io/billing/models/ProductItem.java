package io.billing.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductItem extends Product {
    private int total;

    public ProductItem() {
    }

    public ProductItem(int id, String description, double unitPrice, int stock, int total) {
        super(id, description, unitPrice, stock);
        this.total = total;
    }

    @JsonProperty
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
