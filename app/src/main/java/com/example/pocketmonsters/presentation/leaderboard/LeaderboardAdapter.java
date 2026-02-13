package com.example.pocketmonsters.presentation.leaderboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pocketmonsters.R;
import com.example.pocketmonsters.model.UserDetail;

import java.util.ArrayList;
import java.util.List;

public class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardViewHolder> {
    private final Context context;
    private final LeaderboardClickListener clickListener;
    private final LeaderboardFragment fragment;
    private List<LeaderboardItem> items = new ArrayList<>();

    public void setItems(List<LeaderboardItem> items) {
        this.items = items != null ? items : new ArrayList<>();
        notifyDataSetChanged();
    }

    public LeaderboardAdapter(Context context, LeaderboardClickListener clickListener, LeaderboardFragment fragment) {
        this.context = context;
        this.clickListener = clickListener;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public LeaderboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_leaderboard, parent, false);
        return new LeaderboardViewHolder(view, clickListener, fragment);
    }

    @Override
    public void onBindViewHolder(@NonNull LeaderboardViewHolder holder, int position) {
        LeaderboardItem item = items.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
