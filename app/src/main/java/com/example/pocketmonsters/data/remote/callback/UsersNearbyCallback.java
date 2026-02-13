package com.example.pocketmonsters.data.remote.callback;

import com.example.pocketmonsters.model.UserNearby;

import java.util.List;

public interface UsersNearbyCallback {
    void onUsersNearbySuccess(List<UserNearby> usersNearby);
    void onError(Throwable throwable);
}
