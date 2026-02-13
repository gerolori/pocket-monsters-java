package com.example.pocketmonsters.data.local.virtualitem;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {VirtualItem.class}, version = 1)
public abstract class VirtualItemDB extends RoomDatabase {
    public abstract VirtualItemDAO virtualItemDAO();
    private static VirtualItemDB instance;
    public static synchronized VirtualItemDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), VirtualItemDB.class, "virtualitem_database").fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
