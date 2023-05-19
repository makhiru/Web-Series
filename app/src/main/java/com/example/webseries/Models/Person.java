package com.example.webseries.Models;

import java.io.Serializable;

public class Person implements Serializable {
    String name;
    Images image;

    public Person(String name, Images image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Images getImage() {
        return image;
    }

    public void setImage(Images image) {
        this.image = image;
    }
}
