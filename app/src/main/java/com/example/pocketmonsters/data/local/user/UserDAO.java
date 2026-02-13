package com.example.pocketmonsters.data.local.user;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.google.common.util.concurrent.ListenableFuture;

@Dao
public interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    ListenableFuture<Void> insertAll(User... users);

    @Query("SELECT * FROM user WHERE uid = :uid")
    public ListenableFuture<User> getUserById(int uid);
}