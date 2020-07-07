package com.example.hailameci.fragments.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hailameci.R;
import com.example.hailameci.fragments.items.PlansItem;
import com.example.hailameci.roomDatabase.Plan;

import java.util.List;

public class PlansAdapter extends RecyclerView.Adapter<PlansAdapter.PlansViewHolder> {

    private List<Plan> plans;

    private OnItemClickListener mListener;

    private Context context;

    public PlansAdapter(Context context) {
        this.context = context;
    }

    public interface OnItemClickListener {
        void onItemClick(Plan plan);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public class PlansViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView_teamHome;
        public TextView textView_teamHome;
        public TextView textView_teamAway;
        public ImageView imageView_teamAway;
        public TextView textView_date;
        public TextView textView_stadium;
        public TextView homeOrAway;
        public TextView ticketPrice;
        public TextView numberOfTickers;
        public TextView totalPrice;
        public TextView arrivalDate;
        public TextView leavingDate;
        public TextView placeToStay;


        public PlansViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            imageView_teamHome = itemView.findViewById(R.id.imageView_team);
            textView_teamHome = itemView.findViewById(R.id.textView_teamName);
            textView_teamAway = itemView.findViewById(R.id.textView_teamAway);
            imageView_teamAway = itemView.findViewById(R.id.imageView_teamAway);
            textView_date = itemView.findViewById(R.id.textView_date);
            textView_stadium = itemView.findViewById(R.id.textView_stadium);
            homeOrAway = itemView.findViewById(R.id.textView_homeOrAway);
            ticketPrice = itemView.findViewById(R.id.textView_ticketPrice);
            numberOfTickers = itemView.findViewById(R.id.textView_numberOfTickets);
            totalPrice = itemView.findViewById(R.id.textView_totalPrice);
            arrivalDate = itemView.findViewById(R.id.textView_dateArrival);
            leavingDate = itemView.findViewById(R.id.textView_dateLeaving);
            placeToStay = itemView.findViewById(R.id.textView_placeToStay);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(plans.get(position));
                        }
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public PlansViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.plans_item, parent, false);
        PlansViewHolder evh = new PlansViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull PlansViewHolder holder, int position) {
        PlansItem currentItem = plans.get(position).convertToPlansItem(context);

        holder.imageView_teamHome.setImageResource(currentItem.getImageTeamHome());
        holder.textView_teamHome.setText(currentItem.getHomeTeam());
        holder.textView_teamAway.setText(currentItem.getAwayTeam());
        holder.imageView_teamAway.setImageResource(currentItem.getImageTeamAway());
        holder.textView_date.setText(currentItem.getDate());
        holder.textView_stadium.setText(currentItem.getStadium());
        holder.homeOrAway.setText(currentItem.isHomeMatch());
        holder.ticketPrice.setText(currentItem.getTicketPrice());
        holder.numberOfTickers.setText(currentItem.getNumberOfTickets());
        holder.totalPrice.setText(currentItem.getTotalPrice());
        holder.arrivalDate.setText(currentItem.getArrivalDate());
        holder.leavingDate.setText(currentItem.getLeavingDate());
        holder.placeToStay.setText(currentItem.getPlaceToStay());
    }

    @Override
    public int getItemCount() {
        return plans != null ? plans.size() : 0;
    }

    public void setPlans(List<Plan> plans) {
        this.plans = plans;
        notifyDataSetChanged();
    }

    public Plan getPlanAt(int position) {
        return plans.get(position);
    }


}
