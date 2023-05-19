package com.example.webseries.Models;

import java.io.Serializable;

public class SearchedShow implements Serializable {

    float score;
    Series show;

    public SearchedShow(float score, Series series) {
        this.score = score;
        this.show = series;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public Series getSeries() {
        return show;
    }

    public void setSeries(Series series) {
        this.show = series;
    }
}
