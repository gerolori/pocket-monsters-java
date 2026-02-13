package com.example.pocketmonsters.presentation.leaderboard;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pocketmonsters.R;

public class LeaderboardFragment extends Fragment {
    LeaderboardViewModel viewModel;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public LeaderboardFragment() {
        // Required empty public constructor
    }

    public static LeaderboardFragment newInstance(String param1, String param2) {
        LeaderboardFragment fragment = new LeaderboardFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout first
        View view = inflater.inflate(R.layout.fragment_leaderboard, container, false);

        viewModel = new ViewModelProvider(this).get(LeaderboardViewModel.class);
        viewModel.setLeaderboardFragment(this);

        viewModel.getLeaderboard().observe(getViewLifecycleOwner(), leaderboard -> {
            RecyclerView recyclerView = view.findViewById(R.id.leaderboardRecyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

            LeaderboardAdapter adapter = new LeaderboardAdapter(getContext(),
                    position -> Log.d("LeaderboardFragment", "Clicked on user at position " + position),
                    this);
            adapter.setItems(leaderboard);
            recyclerView.setAdapter(adapter);
        });

        return view;
    }
}
