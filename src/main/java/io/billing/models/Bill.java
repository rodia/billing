package io.billing.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;
import java.util.Date;

public class Bill {
    private int id;
    private String billNumber;
    private Date date;
    private Collection<Item> items;
    private Client client;

    public Bill() {
    }

    public Bill(String billNumber, Date date, Collection<Item> items, Client client) {
        this.billNumber = billNumber;
        this.date = date;
        this.items = items;
        this.client = client;
    }

    public Bill(String billNumber, Date date, Client client) {
        this.billNumber = billNumber;
        this.date = date;
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
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
