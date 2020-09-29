package io.billing.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClientBuy extends Client {
    private double payed;

    public ClientBuy() {
    }

    public ClientBuy(int id, String lastname, String firstname, String nit, double payed) {
        super(id, lastname, firstname, nit);
        this.payed = payed;
    }

    @JsonProperty
    public double getPayed() {
        return payed;
    }

    public void setPayed(double payed) {
        this.payed = payed;
    }
}
