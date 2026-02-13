package com.example.pocketmonsters.data.remote.callback;

import com.example.pocketmonsters.model.VirtualItemDetail;

public interface VirtualItemDetailCallback {
    void onVirtualItemDetailSuccess(VirtualItemDetail virtualItemDetail);
    void onError(Throwable throwable);
}
