package com.example.webseries.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.webseries.Models.Series;
import com.example.webseries.R;
import com.example.webseries.Activity.Sub_Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Movie_Adapter extends RecyclerView.Adapter<Movie_Adapter.ViewHolder> {

    Context context;
    List<Series> arrseries;

    public Movie_Adapter(Context context, List<Series> arrseries) {
        this.context = context;
        this.arrseries = arrseries;
    }

    @NonNull
    @Override
    public Movie_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Movie_Adapter.ViewHolder holder, int position) {

        try {
            Picasso.get().load(arrseries.get(position).getImage().getOriginal()).placeholder(R.drawable.placeholder).into(holder.image);
        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.txtname.setText(arrseries.get(position).getName());

        String type = "";
        for (int i = 0; i < arrseries.get(position).getGenres().length; i++) {
            if (i == arrseries.get(position).getGenres().length - 1) {
                type += arrseries.get(position).getGenres()[i];
            } else {
                type += arrseries.get(position).getGenres()[i] + ", ";
            }
        }
        holder.txttype.setText(type);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, Sub_Movie.class);
                intent.putExtra("Movie", arrseries.get(holder.getAdapterPosition()));
                context.startActivity(intent);
            }
        });
    }

    public void filter(List<Series> filteredList) {
        arrseries = filteredList;
        notifyDataSetChanged();
    }

    public void reset(List<Series> oldShows) {
        arrseries = oldShows;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return arrseries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView txtname, txttype;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.img);
            txtname = itemView.findViewById(R.id.txtname);
            txttype = itemView.findViewById(R.id.txttype);
        }
    }
}
