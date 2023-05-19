package com.example.webseries.Models;

import java.io.Serializable;

public class Series implements Serializable {
    int id;
    String name, type, language, premiered, ended, officialSite, status, summary;
    String[] genres;
    int runtime;
    Images image;
    Rating rating;
    Network network;

    public Series(int id, String name, String type, String language, String premiered, String ended, String officialSite, String status, String summary, String[] genres, int runtime, Images image, Rating rating, Network network) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.language = language;
        this.premiered = premiered;
        this.ended = ended;
        this.officialSite = officialSite;
        this.status = status;
        this.summary = summary;
        this.genres = genres;
        this.runtime = runtime;
        this.image = image;
        this.rating = rating;
        this.network = network;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPremiered() {
        return premiered;
    }

    public void setPremiered(String premiered) {
        this.premiered = premiered;
    }

    public String getEnded() {
        return ended;
    }

    public void setEnded(String ended) {
        this.ended = ended;
    }

    public String getOfficialSite() {
        return officialSite;
    }

    public void setOfficialSite(String officialSite) {
        this.officialSite = officialSite;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String[] getGenres() {
        return genres;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public Images getImage() {
        return image;
    }

    public void setImage(Images image) {
        this.image = image;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }
}
