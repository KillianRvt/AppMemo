package com.killianrvt.memoapplication;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {MemoDTO.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract MemosDAO memosDAO();
}
