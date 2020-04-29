package com.killianrvt.memoapplication;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "memos")
public class MemoDTO {

    // Propriétés
    @PrimaryKey(autoGenerate = true)
    public long memoId = 0;
    private String intitule;

    // Constructeur vide
    public MemoDTO(){}

    // Constructeur
    public MemoDTO(String intitule){
        this.intitule = intitule;
    }

    // Getter
    public String getIntitule(){
        return intitule;
    }

    // Setter
    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }
}
