package com.example.webseries.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.webseries.Adapter.Cast_Adapter;
import com.example.webseries.Models.Casts;
import com.example.webseries.Models.Series;
import com.example.webseries.R;
import com.example.webseries.Retro;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Sub_Movie extends AppCompatActivity {

    List<Casts> castsList = new ArrayList<>();
    Cast_Adapter adapter;

    Series Movie;
    ImageView imgback, imgfront, btnback;
    ProgressBar progressBar;
    RecyclerView peopleRecycler;
    TextView txtname, txttype, txtLanguage, txtstatus, txtPremiered, txtEnded, txtRuntime, txtRating,
            txtSite, txtdis, cast_textv, txtcountry, txtcode, txttime;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_movie_activity);

        getSupportActionBar().hide();

        imgback = findViewById(R.id.imgback);
        imgfront = findViewById(R.id.imgfront);
        btnback = findViewById(R.id.backBtn);
        txtname = findViewById(R.id.txtname);
        txtLanguage = findViewById(R.id.txtLanguage);
        txttype = findViewById(R.id.txttype);
        txtstatus = findViewById(R.id.txtStatus);
        txtPremiered = findViewById(R.id.txtPremiered);
        txtEnded = findViewById(R.id.txtEnded);
        txtRuntime = findViewById(R.id.txtRuntime);
        txtRating = findViewById(R.id.txtRating);
        txtSite = findViewById(R.id.txtSite);
        txtdis = findViewById(R.id.txtDis);
        txtcountry = findViewById(R.id.txtCountry);
        txtcode = findViewById(R.id.txtcode);
        txttime = findViewById(R.id.txttime);
        cast_textv = findViewById(R.id.cast_textv);
        peopleRecycler = findViewById(R.id.peoplesRecycler);
        progressBar = findViewById(R.id.progress);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Movie = (Series) getIntent().getSerializableExtra("Movie");

        Retro.getInstance().retroAPI.getCasts(Movie.getId()).enqueue(new Callback<List<Casts>>() {
            @Override
            public void onResponse(Call<List<Casts>> call, Response<List<Casts>> response) {
                castsList.addAll(response.body());
            }

            @Override
            public void onFailure(Call<List<Casts>> call, Throwable t) {

            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
                if (castsList != null) {
                    findViewById(R.id.cast_textv).setVisibility(View.VISIBLE);
                    peopleRecycler.setLayoutManager(new LinearLayoutManager(Sub_Movie.this, LinearLayoutManager.HORIZONTAL, false));
                    adapter = new Cast_Adapter(Sub_Movie.this, castsList);
                    peopleRecycler.setAdapter(adapter);
                }
            }
        }, 5000);

        try {
            Picasso.get().load(Movie.getImage().getOriginal()).placeholder(R.drawable.placeholder).into(imgback);
        } catch (Exception e) {
            imgback.setImageResource(R.drawable.placeholder);
        }

        try {
            Picasso.get().load(Movie.getImage().getOriginal()).placeholder(R.drawable.placeholder).into(imgfront);
        } catch (Exception e) {
            imgfront.setImageResource(R.drawable.placeholder);
        }

        txtname.setText(Movie.getName());

        String type = "";
        for (int i = 0; i < Movie.getGenres().length; i++) {
            if (i == Movie.getGenres().length - 1) {
                type += Movie.getGenres()[i];
            } else {
                type += Movie.getGenres()[i] + ",\n";
            }
        }
        txttype.setText(type);

        txtLanguage.setText(Movie.getLanguage());

        txtstatus.setText(Movie.getStatus());

        txtPremiered.setText(Movie.getPremiered());
        if (Movie.getEnded() != null) {
            txtEnded.setText(Movie.getEnded());
        } else {
            txtEnded.setText("-");
        }

        txtRuntime.setText(String.valueOf(Movie.getRuntime()));

        txtRating.setText(Movie.getRating().getAverage() + "/10");

        if (Movie.getOfficialSite() != null) {
            txtSite.setText(Movie.getOfficialSite());
            txtSite.setTextColor(getResources().getColor(R.color.purple));
            txtSite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(Intent.createChooser(new Intent(Intent.ACTION_VIEW, Uri.parse(Movie.getOfficialSite())), "Open With"));
                }
            });
        } else {
            txtSite.setText("-");
        }

        if (Movie.getSummary() != null) {
            txtdis.setText(HtmlCompat.fromHtml(Movie.getSummary(), 0));
        } else {
            txtdis.setText("-");
        }

        txtcountry.setText(Movie.getNetwork().getCountry().getName());
        txtcode.setText(Movie.getNetwork().getCountry().getCode());
        txttime.setText(Movie.getNetwork().getCountry().getTimezone());

    }
}