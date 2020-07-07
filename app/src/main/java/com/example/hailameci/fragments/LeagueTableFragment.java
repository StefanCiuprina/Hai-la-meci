package com.example.hailameci.fragments;

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

import com.example.hailameci.fragments.items.LeagueTableItem;
import com.example.hailameci.R;
import com.example.hailameci.fragments.adapters.LeagueTableAdapter;
import com.example.hailameci.fragments.items.LeagueTableItemFirebase;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;

public class LeagueTableFragment extends Fragment {

    private static final String[] playOffTeams = {"astra", "botosani", "cfr", "craiova", "fcsb", "gazmetan"};

    public static final String KEY_NAME = "name";
    public static final String KEY_POSITION = "position";
    public static final String KEY_M = "m";
    public static final String KEY_V = "v";
    public static final String KEY_E = "e";
    public static final String KEY_I = "i";
    public static final String KEY_G = "g";
    public static final String KEY_P = "p";

    private RecyclerView mRecyclerView;
    private RecyclerView mRecyclerView_playout;
    private RecyclerView mRecyclerView_normal;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.Adapter mAdapter_playout;
    private RecyclerView.Adapter mAdapter_normal;

    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.LayoutManager mLayoutManager_playout;
    private RecyclerView.LayoutManager mLayoutManager_normal;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference leagueTableRef = db.collection("league_table");
    private CollectionReference leagueTablePlayoutRef = db.collection("league_table_playout");
    private CollectionReference leagueTableNormalRef = db.collection("league_table_normal");


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_league_table, container, false);
        mRecyclerView = rootView.findViewById(R.id.recyclerView_playoff_table);
        mRecyclerView_playout = rootView.findViewById(R.id.recyclerView_playout_table);
        mRecyclerView_normal = rootView.findViewById(R.id.recyclerView_league_table);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        loadPlayoff();
        loadPlayout();
        loadNormal();

    }

    private void loadPlayoff() {
        final ArrayList<LeagueTableItem> playoffItems = new ArrayList<>();
        leagueTableRef.addSnapshotListener(getActivity(), new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots, @javax.annotation.Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    return;
                }
                playoffItems.clear();
                playoffItems.add(new LeagueTableItem(getContext()));
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    LeagueTableItemFirebase leagueTableItemFirebase = documentSnapshot.toObject(LeagueTableItemFirebase.class);

                    int position = leagueTableItemFirebase.getPosition();
                    String name = leagueTableItemFirebase.getName();
                    int M = leagueTableItemFirebase.getM();
                    int V = leagueTableItemFirebase.getV();
                    int E = leagueTableItemFirebase.getE();
                    int I = leagueTableItemFirebase.getI();
                    int G = leagueTableItemFirebase.getG();
                    int P = leagueTableItemFirebase.getP();
                    playoffItems.add(new LeagueTableItem(getContext(), position, name, M, V, E, I, G, P));
                }

                Collections.sort(playoffItems);

                mRecyclerView.setHasFixedSize(true);
                mLayoutManager = new LinearLayoutManager(getContext());
                mAdapter = new LeagueTableAdapter(playoffItems);

                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setAdapter(mAdapter);
            }
        });
    }

    private void loadPlayout() {
        final ArrayList<LeagueTableItem> playoutItems = new ArrayList<>();
        leagueTablePlayoutRef.addSnapshotListener(getActivity(), new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots, @javax.annotation.Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    return;
                }
                playoutItems.clear();
                playoutItems.add(new LeagueTableItem(getContext()));
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    LeagueTableItemFirebase leagueTableItemFirebase = documentSnapshot.toObject(LeagueTableItemFirebase.class);

                    int position = leagueTableItemFirebase.getPosition();
                    String name = leagueTableItemFirebase.getName();
                    int M = leagueTableItemFirebase.getM();
                    int V = leagueTableItemFirebase.getV();
                    int E = leagueTableItemFirebase.getE();
                    int I = leagueTableItemFirebase.getI();
                    int G = leagueTableItemFirebase.getG();
                    int P = leagueTableItemFirebase.getP();
                    playoutItems.add(new LeagueTableItem(getContext(), position, name, M, V, E, I, G, P));
                }

                Collections.sort(playoutItems);

                mRecyclerView_playout.setHasFixedSize(true);
                mLayoutManager_playout = new LinearLayoutManager(getContext());
                mAdapter_playout = new LeagueTableAdapter(playoutItems);

                mRecyclerView_playout.setLayoutManager(mLayoutManager_playout);
                mRecyclerView_playout.setAdapter(mAdapter_playout);
            }
        });
    }

    private void loadNormal() {
        final ArrayList<LeagueTableItem> normalLeagueItems = new ArrayList<>();
        leagueTableNormalRef.addSnapshotListener(getActivity(), new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots, @javax.annotation.Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    return;
                }
                normalLeagueItems.clear();
                normalLeagueItems.add(new LeagueTableItem(getContext()));
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    LeagueTableItemFirebase leagueTableItemFirebase = documentSnapshot.toObject(LeagueTableItemFirebase.class);

                    int position = leagueTableItemFirebase.getPosition();
                    String name = leagueTableItemFirebase.getName();
                    int M = leagueTableItemFirebase.getM();
                    int V = leagueTableItemFirebase.getV();
                    int E = leagueTableItemFirebase.getE();
                    int I = leagueTableItemFirebase.getI();
                    int G = leagueTableItemFirebase.getG();
                    int P = leagueTableItemFirebase.getP();
                    normalLeagueItems.add(new LeagueTableItem(getContext(), position, name, M, V, E, I, G, P));
                }

                Collections.sort(normalLeagueItems);

                mRecyclerView_normal.setHasFixedSize(true);
                mLayoutManager_normal = new LinearLayoutManager(getContext());
                mAdapter_normal = new LeagueTableAdapter(normalLeagueItems);

                mRecyclerView_normal.setLayoutManager(mLayoutManager_normal);
                mRecyclerView_normal.setAdapter(mAdapter_normal);
            }
        });
    }

    public void loadNote(View rootView) {
        final ArrayList<LeagueTableItem> playoffItems = new ArrayList<>();
        playoffItems.add(new LeagueTableItem(getContext()));
        for (int i = 0; i < 6; i++) {

            DocumentReference noteRef = db.collection("league_table").document(playOffTeams[i]);


            noteRef.get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()) {
                                int position = (int) (double) documentSnapshot.getDouble(KEY_POSITION);
                                String name = documentSnapshot.getString(KEY_NAME);
                                int m = (int) (double) documentSnapshot.getDouble(KEY_M);
                                int v = (int) (double) documentSnapshot.getDouble(KEY_V);
                                int e = (int) (double) documentSnapshot.getDouble(KEY_E);
                                int i = (int) (double) documentSnapshot.getDouble(KEY_I);
                                int g = (int) (double) documentSnapshot.getDouble(KEY_G);
                                int p = (int) (double) documentSnapshot.getDouble(KEY_P);
                                Toast.makeText(getContext(), name, Toast.LENGTH_SHORT).show();
                                playoffItems.add(new LeagueTableItem(getContext(), position, name, m, v, e, i, g, p));
                            } else {
                                //Toast.makeText(getContext(), "Document does not exist", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getContext(), "Error!", Toast.LENGTH_SHORT).show();
                }
            });
        }
        Collections.sort(playoffItems);

        mRecyclerView = rootView.findViewById(R.id.recyclerView_playoff_table);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new LeagueTableAdapter(playoffItems);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}
