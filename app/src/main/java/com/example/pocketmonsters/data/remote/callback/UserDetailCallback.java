package com.example.pocketmonsters.data.remote.callback;

import com.example.pocketmonsters.model.UserDetail;

public interface UserDetailCallback {
    void onUserDetailSuccess(UserDetail userDetail);
    void onError(Throwable throwable);
}
