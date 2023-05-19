package com.example.webseries.Models;

import android.media.Image;

import java.io.Serializable;

public class Character implements Serializable {

    String name;
    Images image;

    public Character(String name, Images image) {
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
