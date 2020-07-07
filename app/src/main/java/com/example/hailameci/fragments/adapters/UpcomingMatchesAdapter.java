package com.example.hailameci.fragments.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hailameci.R;
import com.example.hailameci.fragments.items.UpcomingMatchesItem;

import java.util.ArrayList;
import java.util.List;

public class UpcomingMatchesAdapter extends RecyclerView.Adapter<UpcomingMatchesAdapter.UpcomingMatchesViewHolder> {

    private ArrayList<UpcomingMatchesItem> upcomingMatchesItems;

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class UpcomingMatchesViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView_teamHome;
        public TextView textView_teamHome;
        public TextView textView_teamAway;
        public ImageView imageView_teamAway;
        public TextView textView_date;
        public TextView textView_stadium;

        public UpcomingMatchesViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            imageView_teamHome = itemView.findViewById(R.id.imageView_team);
            textView_teamHome = itemView.findViewById(R.id.textView_teamName);
            textView_teamAway = itemView.findViewById(R.id.textView_teamAway);
            imageView_teamAway = itemView.findViewById(R.id.imageView_teamAway);
            textView_date = itemView.findViewById(R.id.textView_date);
            textView_stadium = itemView.findViewById(R.id.textView_stadium);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public UpcomingMatchesAdapter(ArrayList<UpcomingMatchesItem> upcomingMatchesItems) {
        this.upcomingMatchesItems = upcomingMatchesItems;
    }

    @NonNull
    @Override
    public UpcomingMatchesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.upcoming_matches_item, parent, false);
        UpcomingMatchesViewHolder evh = new UpcomingMatchesViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull UpcomingMatchesViewHolder holder, int position) {
        UpcomingMatchesItem currentItem = upcomingMatchesItems.get(position);

        holder.imageView_teamHome.setImageResource(currentItem.getImageTeamHome());
        holder.textView_teamHome.setText(currentItem.getNameTeamHome());
        holder.textView_teamAway.setText(currentItem.getNameTeamAway());
        holder.imageView_teamAway.setImageResource(currentItem.getImageTeamAway());
        holder.textView_date.setText(currentItem.getDate());
        holder.textView_stadium.setText(currentItem.getStadium());
    }

    @Override
    public int getItemCount() {
        return upcomingMatchesItems.size();
    }
}
