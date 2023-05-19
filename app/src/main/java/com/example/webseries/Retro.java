package com.example.webseries;

import com.example.webseries.Models.Casts;
import com.example.webseries.Models.SearchedShow;
import com.example.webseries.Models.Series;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class Retro {

    public String url = "https://api.tvmaze.com/";
    public static Retro instance;
    public RetroAPI retroAPI;

    public Retro() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retroAPI = retrofit.create(RetroAPI.class);

    }

    public static Retro getInstance() {
        if (instance == null) {
            instance = new Retro();
        }
        return instance;
    }

    public interface RetroAPI {

        @GET("shows")
        Call<List<Series>> getallSeries();

        @GET("search/sh ows")
        Call<List<SearchedShow>> getSearchedShows(@Query("q") String name);

        @GET("shows/{id}/cast")
        Call<List<Casts>> getCasts(@Path("id") int id);
    }
}
