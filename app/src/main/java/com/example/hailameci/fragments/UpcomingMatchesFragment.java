package com.example.hailameci.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hailameci.R;
import com.example.hailameci.fragments.adapters.UpcomingMatchesAdapter;
import com.example.hailameci.fragments.items.UpcomingMatchesItem;
import com.example.hailameci.activities.AddEditPlanActivity;
import com.example.hailameci.roomDatabase.Plan;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class UpcomingMatchesFragment extends Fragment {

    public static final int ADD_PLAN_REQUEST = 1;
    public static final String EXTRA_TEAMHOME = "com.example.hailameci.fragments.EXTRA_TEAMHOME";
    public static final String EXTRA_TEAMAWAY = "com.example.hailameci.fragments.EXTRA_TEAMAWAY";
    public static final String EXTRA_DATE = "com.example.hailameci.fragments.EXTRA_DATE";
    public static final String EXTRA_STADIUM = "com.example.hailameci.fragments.EXTRA_STADIUM";

    private RecyclerView mRecyclerView;
    private UpcomingMatchesAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private FragmentAListener listener;
    public interface FragmentAListener {
        void onInputASent(Plan input);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_upcoming_matches, container, false);

        final ArrayList<UpcomingMatchesItem> upcomingMatchesItems = new ArrayList<>();

        upcomingMatchesItems.add(new UpcomingMatchesItem(R.drawable.cl_viitorul, getString(R.string.viitorul),
                getString(R.string.clinceni), R.drawable.cl_clinceni, "10/07/2020", getString(R.string.viitorul_stadium)));
        upcomingMatchesItems.add(new UpcomingMatchesItem(R.drawable.cl_botosani, getString(R.string.botosani),
                getString(R.string.astra), R.drawable.cl_astra, "10/07/2020", getString(R.string.botosani_stadium)));
        upcomingMatchesItems.add(new UpcomingMatchesItem(R.drawable.cl_hermannstadt, getString(R.string.hermannstadt),
                getString(R.string.voluntari), R.drawable.cl_voluntari, "11/07/2020", getString(R.string.hermannstadt_stadium)));
        upcomingMatchesItems.add(new UpcomingMatchesItem(R.drawable.cl_dinamo, getString(R.string.dinamo),
                getString(R.string.iasi), R.drawable.cl_iasi, "12/07/2020", getString(R.string.dinamo_stadium)));
        upcomingMatchesItems.add(new UpcomingMatchesItem(R.drawable.cl_craiova, getString(R.string.craiova),
                getString(R.string.fcsb), R.drawable.cl_fcsb, "12/07/2020", getString(R.string.craiova_stadium)));
        upcomingMatchesItems.add(new UpcomingMatchesItem(R.drawable.cl_sepsi, getString(R.string.sepsi),
                getString(R.string.chindia), R.drawable.cl_chindia, "13/07/2020", getString(R.string.sepsi_stadium)));
        upcomingMatchesItems.add(new UpcomingMatchesItem(R.drawable.cl_cfr, getString(R.string.cfr),
                getString(R.string.gazmetan), R.drawable.cl_gazmetan, "13/07/2020", getString(R.string.cfr_stadium)));

        mRecyclerView = rootView.findViewById(R.id.recyclerView_upcomingMatches);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new UpcomingMatchesAdapter(upcomingMatchesItems);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new UpcomingMatchesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(), AddEditPlanActivity.class);
                intent.putExtra(EXTRA_TEAMHOME, upcomingMatchesItems.get(position).getNameTeamHome());
                intent.putExtra(EXTRA_TEAMAWAY, upcomingMatchesItems.get(position).getNameTeamAway());
                intent.putExtra(EXTRA_DATE, upcomingMatchesItems.get(position).getDate());
                intent.putExtra(EXTRA_STADIUM, upcomingMatchesItems.get(position).getStadium());
                startActivityForResult(intent, ADD_PLAN_REQUEST);
            }
        });

        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == UpcomingMatchesFragment.ADD_PLAN_REQUEST && resultCode == RESULT_OK) {
            String homeTeam = data.getStringExtra(AddEditPlanActivity.EXTRA_TEAMHOME);
            String awayTeam = data.getStringExtra(AddEditPlanActivity.EXTRA_TEAMAWAY);
            String date = data.getStringExtra(AddEditPlanActivity.EXTRA_DATE);
            String stadium = data.getStringExtra(AddEditPlanActivity.EXTRA_STADIUM);
            boolean isHomeMatch = data.getBooleanExtra(AddEditPlanActivity.EXTRA_ISHOMEMATCH, true);
            double ticketPrice = data.getDoubleExtra(AddEditPlanActivity.EXTRA_TICKETPRICE, 0);
            int numberOfTickets = data.getIntExtra(AddEditPlanActivity.EXTRA_NUMBEROFTICKETS, 0);
            String arrivalDate = data.getStringExtra(AddEditPlanActivity.EXTRA_ARRIVALDATE);
            String leavingDate = data.getStringExtra(AddEditPlanActivity.EXTRA_LEAVINGDATE);
            String placeToStay = data.getStringExtra(AddEditPlanActivity.EXTRA_PLACETOSTAY);


            Plan plan = new Plan(homeTeam, awayTeam, date, stadium,
                    isHomeMatch, ticketPrice, numberOfTickets, arrivalDate, leavingDate, placeToStay);
            listener.onInputASent(plan);

            Toast.makeText(getContext(), R.string.plan_saved, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), R.string.plan_not_saved, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof FragmentAListener) {
            listener = (FragmentAListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement FragmentAListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
