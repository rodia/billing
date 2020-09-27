package io.billing.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Item {
    private int id;
    private Product product;
    private int quantity;

    public Item() {
    }

    public Item(int id, Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    @JsonProperty
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @JsonProperty
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
