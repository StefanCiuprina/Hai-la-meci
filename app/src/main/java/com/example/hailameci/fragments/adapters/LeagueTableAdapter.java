package com.example.hailameci.fragments.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hailameci.fragments.items.LeagueTableItem;
import com.example.hailameci.R;

import java.util.ArrayList;

public class LeagueTableAdapter extends RecyclerView.Adapter<LeagueTableAdapter.LeagueTableViewHolder> {

    private ArrayList<LeagueTableItem> leagueTableItems;

    public static class LeagueTableViewHolder extends RecyclerView.ViewHolder {

        public TextView textView_position;
        public ImageView imageView_team;
        public TextView textView_teamName;
        public TextView textView_m;
        public TextView textView_v;
        public TextView textView_e;
        public TextView textView_i;
        public TextView textView_g;
        public TextView textView_p;

        public LeagueTableViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_position = itemView.findViewById(R.id.textView_position);
            imageView_team = itemView.findViewById(R.id.imageView_team);
            textView_teamName = itemView.findViewById(R.id.textView_teamName);
            textView_m = itemView.findViewById(R.id.textView_m);
            textView_v = itemView.findViewById(R.id.textView_v);
            textView_e = itemView.findViewById(R.id.textView_e);
            textView_i = itemView.findViewById(R.id.textView_i);
            textView_g = itemView.findViewById(R.id.textView_g);
            textView_p = itemView.findViewById(R.id.textView_p);
        }
    }

    public LeagueTableAdapter(ArrayList<LeagueTableItem> leagueTableItems) {
        this.leagueTableItems = leagueTableItems;
    }

    @NonNull
    @Override
    public LeagueTableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.league_table_item, parent, false);
        LeagueTableViewHolder evh = new LeagueTableViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull LeagueTableViewHolder holder, int position) {
        LeagueTableItem currentItem = leagueTableItems.get(position);

        holder.textView_position.setText(currentItem.getPosition());
        holder.imageView_team.setImageResource(currentItem.getImage());
        holder.textView_teamName.setText(currentItem.getTeamName());
        holder.textView_m.setText(currentItem.getM());
        holder.textView_v.setText(currentItem.getV());
        holder.textView_e.setText(currentItem.getE());
        holder.textView_i.setText(currentItem.getI());
        holder.textView_g.setText(currentItem.getG());
        holder.textView_p.setText(currentItem.getP());
    }

    @Override
    public int getItemCount() {
        return leagueTableItems.size();
    }
}
