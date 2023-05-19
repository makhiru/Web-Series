package com.example.webseries.Models;

import java.io.Serializable;

public class Images implements Serializable {

    String original;

    public Images(String original) {
        this.original = original;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }
}
