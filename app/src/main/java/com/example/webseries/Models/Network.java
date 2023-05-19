package com.example.webseries.Models;

import java.io.Serializable;

public class Network implements Serializable {

    Country country;

    public Network(Country country) {
        this.country = country;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
