package com.example.pocketmonsters.data.remote.callback;

import com.example.pocketmonsters.model.VirtualItemNearby;

import java.util.List;

public interface VirtualItemNearbyCallback {
    void onVirtualItemsNearbySuccess(List<VirtualItemNearby> virtualItemNearby);
    void onError(Throwable throwable);
}
