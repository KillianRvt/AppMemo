package com.killianrvt.memoapplication;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public abstract class MemosDAO {

    @Query("SELECT * FROM memos")
    public abstract List<MemoDTO> getListeMemos();

    @Query("SELECT COUNT(*) FROM memos WHERE intitule = :intitule")
    public abstract long countMemosParIntitule(String intitule);

    @Insert
    public abstract void insert(MemoDTO... memos);

    @Update
    public abstract void update(MemoDTO... memos);

    @Delete
    public abstract void delete(MemoDTO... memos);
}
