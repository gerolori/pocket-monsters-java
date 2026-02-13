package com.example.pocketmonsters.data.local.virtualitem;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

@Dao
public interface VirtualItemDAO {
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    ListenableFuture<Void> insertAll(VirtualItem... virtualItems);

    @Query("SELECT * FROM virtualitem")
    public ListenableFuture<List<VirtualItem>> getAll();

    @Query("SELECT * FROM virtualitem WHERE id = :id")
    public ListenableFuture<VirtualItem> getVirtualItemById(int id);
}
