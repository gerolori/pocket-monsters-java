package com.example.pocketmonsters.presentation.leaderboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class LeaderboardViewModel extends ViewModel {
    private LeaderboardFragment fragment;
    private final MutableLiveData<List<LeaderboardItem>> _leaderboard = new MutableLiveData<>();

    public void setLeaderboardFragment(LeaderboardFragment fragment) {
        this.fragment = fragment;
    }

    public LiveData<List<LeaderboardItem>> getLeaderboard() {
        return _leaderboard;
    }

    public void loadLeaderboard() {
        // TODO: Implement data loading logic
        // Example:
        // _leaderboard.setValue(repository.getLeaderboardData());
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        fragment = null;
    }
}
