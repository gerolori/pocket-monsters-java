package com.example.pocketmonsters.data.local.user;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 1)
public abstract class UserDB extends RoomDatabase {
    public abstract UserDAO userDAO();
    private static UserDB instance;
    public static synchronized UserDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), UserDB.class, "user_database").fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
