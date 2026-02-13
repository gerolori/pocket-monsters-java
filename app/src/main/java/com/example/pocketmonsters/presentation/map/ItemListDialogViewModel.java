package com.example.pocketmonsters.presentation.map;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pocketmonsters.data.local.virtualitem.VirtualItem;
import com.example.pocketmonsters.data.local.virtualitem.VirtualItemDB;
import com.google.android.gms.maps.model.LatLng;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

public class ItemListDialogViewModel extends ViewModel {
    MutableLiveData<List<VirtualItem>> virtualItems;
    private ItemListDialogFragment fragment;
    private Context context;
    private int distance;
    private LatLng position;


    public ItemListDialogViewModel() {
        virtualItems = new MutableLiveData<>();
        pullVirtualItems();
    }

    public LiveData<List<VirtualItem>> getVirtualItems() {
        return virtualItems;
    }

    public ItemListDialogFragment getItemListDialogFragment() {
        return fragment;
    }

    public VirtualItem getItemPosition(int position) {
        return virtualItems.getValue().get(position);
    }

    public int countItems() {
        return virtualItems.getValue().size();
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public LatLng getPosition() {
        return position;
    }

    public void setPosition(LatLng position) {
        this.position = position;
    }

    public void setItemListDialogFragment(ItemListDialogFragment itemListDialogFragment) {
        this.fragment = itemListDialogFragment;
    }

    private void pullVirtualItems() {
        VirtualItemDB db = VirtualItemDB.getInstance(context);
        ListenableFuture<List<VirtualItem>> future = db.virtualItemDAO().getAll();
        List<VirtualItem> items = Futures.getUnchecked(future);
        virtualItems.postValue(items);
    }
}
