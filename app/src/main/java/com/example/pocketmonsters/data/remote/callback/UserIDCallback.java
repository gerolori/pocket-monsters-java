package com.example.pocketmonsters.data.remote.callback;

import com.example.pocketmonsters.model.UserID;

public interface UserIDCallback {
    void onUserIDSuccess(UserID userID);
    void onError(Throwable throwable);
}
