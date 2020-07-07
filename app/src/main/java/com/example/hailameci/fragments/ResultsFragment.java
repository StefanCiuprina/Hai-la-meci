package com.example.hailameci.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hailameci.R;
import com.example.hailameci.fragments.items.ResultsItem;
import com.example.hailameci.fragments.adapters.ResultsAdapter;

import java.util.ArrayList;

public class ResultsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_results, container, false);

        ArrayList<ResultsItem> resultsItems = new ArrayList<>();


        resultsItems.add(new ResultsItem(R.drawable.cl_voluntari, getString(R.string.voluntari),
                getString(R.string.viitorul), R.drawable.cl_viitorul, "06/07/2020", getString(R.string.voluntari_stadium),
                0, 0));
        resultsItems.add(new ResultsItem(R.drawable.cl_chindia, getString(R.string.chindia),
                getString(R.string.hermannstadt), R.drawable.cl_hermannstadt, "06/07/2020", getString(R.string.chindia_stadium),
                0, 1));
        resultsItems.add(new ResultsItem(R.drawable.cl_clinceni, getString(R.string.clinceni),
                getString(R.string.dinamo), R.drawable.cl_dinamo, "05/07/2020", getString(R.string.clinceni_stadium),
                1, 3));
        resultsItems.add(new ResultsItem(R.drawable.cl_iasi, getString(R.string.iasi),
                getString(R.string.sepsi), R.drawable.cl_sepsi, "05/07/2020", getString(R.string.iasi_stadium),
                3, 1));
        resultsItems.add(new ResultsItem(R.drawable.cl_astra, getString(R.string.astra),
                getString(R.string.cfr), R.drawable.cl_cfr, "05/07/2020", getString(R.string.astra_stadium),
                2, 2));
        resultsItems.add(new ResultsItem(R.drawable.cl_fcsb, getString(R.string.fcsb),
                getString(R.string.botosani), R.drawable.cl_botosani, "04/07/2020", getString(R.string.fcsb_stadium),
                1, 1));
        resultsItems.add(new ResultsItem(R.drawable.cl_gazmetan, getString(R.string.gazmetan),
                getString(R.string.craiova), R.drawable.cl_craiova, "03/07/2020", getString(R.string.gazmetan_stadium),
                1, 2));

        mRecyclerView = rootView.findViewById(R.id.recyclerView_results);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new ResultsAdapter(resultsItems);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        return rootView;
    }
}
