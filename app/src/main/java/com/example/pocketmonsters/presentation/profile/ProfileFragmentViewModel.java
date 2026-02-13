package com.example.pocketmonsters.presentation.profile;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProfileFragmentViewModel extends ViewModel {
    private MutableLiveData<String> username = new MutableLiveData<>();

    public MutableLiveData<String> getUsername() {
        if (username == null) {
            username = new MutableLiveData<String>();
            username.setValue("name not found");
        }
        return username;
    }

}
