package com.example.webseries.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.webseries.Models.Casts;
import com.example.webseries.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Cast_Adapter extends RecyclerView.Adapter<Cast_Adapter.ViewHolder> {

    Context context;
    List<Casts> castsList;

    public Cast_Adapter(Context context, List<Casts> castsList) {
        this.context = context;
        this.castsList = castsList;
    }

    @NonNull
    @Override
    public Cast_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cast_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        try {
            holder.card1.setVisibility(View.VISIBLE);
            Picasso.get().load(castsList.get(position).getPerson().getImage().getOriginal())
                    .placeholder(R.drawable.placeholder).into(holder.castimg);

        } catch (Exception e) {
            holder.card1.setVisibility(View.GONE);
        }

        holder.castname.setText(castsList.get(position).getPerson().getName());

        try {
            holder.card2.setVisibility(View.VISIBLE);
            Picasso.get().load(castsList.get(position).getCharacter().getImage().getOriginal())
                    .placeholder(R.drawable.placeholder).into(holder.characterimg);

        } catch (Exception e) {
            holder.card2.setVisibility(View.GONE);
        }

        holder.charactername.setText(castsList.get(position).getCharacter().getName());
    }

    @Override
    public int getItemCount() {
        return castsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView castimg, characterimg;
        TextView castname, charactername;
        CardView card1, card2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            castimg = itemView.findViewById(R.id.cast_img);
            characterimg = itemView.findViewById(R.id.character_img);
            castname = itemView.findViewById(R.id.cast_name);
            charactername = itemView.findViewById(R.id.character_name);
            card1 = itemView.findViewById(R.id.card1);
            card2 = itemView.findViewById(R.id.card2);
        }
    }
}
