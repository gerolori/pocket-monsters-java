package com.example.pocketmonsters.data.remote.callback;

import com.example.pocketmonsters.model.UserRanking;

import java.util.List;

public interface RankingCallback {
    void onRankingSuccess(List<UserRanking> userRankings);
    void onError(Throwable throwable);
}
