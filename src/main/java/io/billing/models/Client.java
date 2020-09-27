package io.billing.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Client {
    private int id;
    private String lastname;
    private String firstname;
    private String nit;

    public Client() {
    }

    public Client(int id, String lastname, String firstname, String nit) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.nit = nit;
    }

    @JsonProperty
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @JsonProperty
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @JsonProperty
    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }
}
