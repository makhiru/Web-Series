package com.example.webseries.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.webseries.Adapter.Cast_Adapter;
import com.example.webseries.Adapter.Movie_Adapter;
import com.example.webseries.Models.SearchedShow;
import com.example.webseries.Models.Series;
import com.example.webseries.R;
import com.example.webseries.Retro;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    List<Series> arrseries = new ArrayList<>();
    List<Series> filterlist = new ArrayList<>();
    Movie_Adapter adapter;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    SearchView searchView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        progressBar = findViewById(R.id.progressbar);
        searchView = findViewById(R.id.searchview);

        progressBar.setVisibility(View.VISIBLE);

        Retro.getInstance().retroAPI.getallSeries().enqueue(new Callback<List<Series>>() {
            @Override
            public void onResponse(Call<List<Series>> call, Response<List<Series>> response) {
                arrseries.addAll(response.body());
                progressBar.setVisibility(View.GONE);
                recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
                adapter = new Movie_Adapter(MainActivity.this, arrseries);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Series>> call, Throwable t) {

            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                Retro.getInstance().retroAPI.getSearchedShows(query).enqueue(new Callback<List<SearchedShow>>() {
                    @Override
                    public void onResponse(Call<List<SearchedShow>> call, Response<List<SearchedShow>> response) {
                        filterlist.clear();
                        for (int i = 0; i < response.body().size(); i++) {
                            filterlist.add(response.body().get(i).getSeries());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<SearchedShow>> call, Throwable t) {

                    }
                });
                progressBar.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        adapter.filter(filterlist);
                        progressBar.setVisibility(View.GONE);
                    }
                }, 3000);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()) {
                    adapter.reset(arrseries);
                }
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        Dialog dialog = new Dialog(MainActivity.this);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.exit_dialogbox);

        dialog.findViewById(R.id.yesBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });
        dialog.findViewById(R.id.noBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}