package com.example.hailameci.fragments.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hailameci.R;
import com.example.hailameci.fragments.items.ResultsItem;

import java.util.ArrayList;

public class ResultsAdapter extends RecyclerView.Adapter<ResultsAdapter.ResultsViewHolder> {

    private ArrayList<ResultsItem> resultsItems;

    public static class ResultsViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView_teamHome;
        public TextView textView_teamHome;
        public TextView textView_teamAway;
        public ImageView imageView_teamAway;
        public TextView textView_date;
        public TextView textView_stadium;
        public TextView textView_scoreHome;
        public TextView textView_scoreAway;

        public ResultsViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView_teamHome = itemView.findViewById(R.id.imageView_team);
            textView_teamHome = itemView.findViewById(R.id.textView_teamName);
            textView_teamAway = itemView.findViewById(R.id.textView_teamAway);
            imageView_teamAway = itemView.findViewById(R.id.imageView_teamAway);
            textView_date = itemView.findViewById(R.id.textView_date);
            textView_stadium = itemView.findViewById(R.id.textView_stadium);

            textView_scoreHome = itemView.findViewById(R.id.textView_scoreHome);
            textView_scoreAway = itemView.findViewById(R.id.textView_scoreAway);
        }
    }

    public ResultsAdapter(ArrayList<ResultsItem> resultsItems) {
        this.resultsItems = resultsItems;
    }

    @NonNull
    @Override
    public ResultsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.results_item, parent, false);
        ResultsViewHolder evh = new ResultsViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ResultsViewHolder holder, int position) {
        ResultsItem currentItem = resultsItems.get(position);

        holder.imageView_teamHome.setImageResource(currentItem.getImageTeamHome());
        holder.textView_teamHome.setText(currentItem.getNameTeamHome());
        holder.textView_teamAway.setText(currentItem.getNameTeamAway());
        holder.imageView_teamAway.setImageResource(currentItem.getImageTeamAway());
        holder.textView_date.setText(currentItem.getDate());
        holder.textView_stadium.setText(currentItem.getStadium());

        holder.textView_scoreHome.setText(currentItem.getScoreHome());
        holder.textView_scoreAway.setText(currentItem.getScoreAway());
    }

    @Override
    public int getItemCount() {
        return resultsItems.size();
    }
}
