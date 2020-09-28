package io.billing.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;

public class Bill {
    private int id;
    private String billNumber;
    private String date;
    private Collection<Item> items;
    private Client client;

    public Bill() {
    }

    public Bill(int id, String billNumber, String date, Client client) {
        this.id = id;
        this.billNumber = billNumber;
        this.date = date;
        this.client = client;
    }

    public Bill(int id, String billNumber, String date, Client client, Collection<Item> items) {
        this.id = id;
        this.billNumber = billNumber;
        this.date = date;
        this.items = items;
        this.client = client;
    }

    @JsonProperty
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @JsonProperty
    public Collection<Item> getItems() {
        return items;
    }

    public void setItems(Collection<Item> items) {
        this.items = items;
    }

    @JsonProperty
    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    @JsonProperty
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
