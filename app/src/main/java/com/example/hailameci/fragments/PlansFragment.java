package com.example.hailameci.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hailameci.R;
import com.example.hailameci.activities.AddEditPlanActivity;
import com.example.hailameci.fragments.adapters.PlansAdapter;
import com.example.hailameci.roomDatabase.Plan;
import com.example.hailameci.roomDatabase.PlanViewModel;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class PlansFragment extends Fragment {

    public static final int EDIT_PLAN_REQUEST = 2;

    private RecyclerView mRecyclerView;
    private PlansAdapter mAdapter;

    private PlanViewModel planViewModel;

    private ArrayList<Plan> newPlans;
    private boolean newPlanInserted = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_plans, container, false);

        mRecyclerView = rootView.findViewById(R.id.recyclerView_plans);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new PlansAdapter(getContext());
        mRecyclerView.setAdapter(mAdapter);

        planViewModel = new ViewModelProvider(this).get(PlanViewModel.class);
        if(newPlanInserted) {
            for(Plan plan : newPlans) {
                planViewModel.insert(plan);
            }
            newPlans = null;
            newPlanInserted = false;
        }
        planViewModel.getAllPlans().observe(getViewLifecycleOwner(), new Observer<List<Plan>>() {
            @Override
            public void onChanged(List<Plan> plans) {
                mAdapter.setPlans(plans);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                planViewModel.delete(mAdapter.getPlanAt(viewHolder.getAdapterPosition()));
                Toast.makeText(getContext(), getString(R.string.plan_deleted), Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(mRecyclerView);

        mAdapter.setOnItemClickListener(new PlansAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Plan plan) {
                Intent intent = new Intent(getContext(), AddEditPlanActivity.class);
                intent.putExtra(AddEditPlanActivity.EXTRA_ID, plan.getId());
                intent.putExtra(AddEditPlanActivity.EXTRA_TEAMHOME, plan.getHomeTeam());
                intent.putExtra(AddEditPlanActivity.EXTRA_TEAMAWAY, plan.getAwayTeam());
                intent.putExtra(AddEditPlanActivity.EXTRA_DATE, plan.getDate());
                intent.putExtra(AddEditPlanActivity.EXTRA_STADIUM, plan.getStadium());
                intent.putExtra(AddEditPlanActivity.EXTRA_ISHOMEMATCH, plan.isHomeMatch());
                intent.putExtra(AddEditPlanActivity.EXTRA_TICKETPRICE, plan.getTicketPrice());
                intent.putExtra(AddEditPlanActivity.EXTRA_NUMBEROFTICKETS, plan.getNumberOfTickets());
                intent.putExtra(AddEditPlanActivity.EXTRA_ARRIVALDATE, plan.getArrivalDate());
                intent.putExtra(AddEditPlanActivity.EXTRA_LEAVINGDATE, plan.getLeavingDate());
                intent.putExtra(AddEditPlanActivity.EXTRA_PLACETOSTAY, plan.getPlaceToStay());
                startActivityForResult(intent, EDIT_PLAN_REQUEST);
            }
        });

        return rootView;
    }

    public void insertNewPlan(Plan plan) {
        if(newPlans == null) {
            newPlans = new ArrayList<>();
        }
        newPlans.add(plan);
        newPlanInserted = true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == EDIT_PLAN_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(AddEditPlanActivity.EXTRA_ID, -1);

            if(id == -1) {
                Toast.makeText(getContext(), getString(R.string.plan_cant_be_updated), Toast.LENGTH_SHORT).show();
                return;
            }

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
            plan.setId(id);
            planViewModel.update(plan);
            Toast.makeText(getContext(), R.string.plan_updated, Toast.LENGTH_SHORT).show();
            
        } else {
            Toast.makeText(getContext(), R.string.plan_not_updated, Toast.LENGTH_SHORT).show();
        }
    }

}
