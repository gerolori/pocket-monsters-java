package com.example.pocketmonsters.data.remote.callback;

import com.example.pocketmonsters.model.VirtualItemActivated;

public interface VirtualItemActivatedCallback {
    void onActivatedVirtualItemSuccess(VirtualItemActivated virtualItemActivated);
    void onError(Throwable throwable);
}
