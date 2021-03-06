package com.rominichhh.todoapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.rominichhh.todoapp.models.Task;

@Database(entities = {Task.class}, version = 1)
public abstract class LocalDatabase extends RoomDatabase {

    private static LocalDatabase sInstance;

    public static LocalDatabase getInstance(Context context) {
        // el allow en una app productiva no va
        if (sInstance == null) {
            sInstance = Room.databaseBuilder(context, LocalDatabase.class, "LocalDatabase")
                    .allowMainThreadQueries()
                    .build();

        }
        return sInstance;
    }

    public abstract TasksDao getTaskDao();
}
