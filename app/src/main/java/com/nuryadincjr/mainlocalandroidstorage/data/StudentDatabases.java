package com.nuryadincjr.mainlocalandroidstorage.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Student.class}, version = 1)
public abstract class StudentDatabases extends RoomDatabase {
    public abstract StudentDao studentDao();

    private static StudentDatabases instance;

    public static StudentDatabases getInstance(Context context) {
        if (instance == null) {
            synchronized (StudentDatabases.class) {
                instance = Room.databaseBuilder(context.getApplicationContext(),
                        StudentDatabases.class, "studentdb").build();
            }
        }
        return instance;
    }
}
