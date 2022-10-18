package com.example.exams.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "class")
public class ClassEntity{

    public ClassEntity(String className){
        this.className = className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @PrimaryKey(autoGenerate = true)
    private int id_Class;

    public String getClassName() {
        return className;
    }

    @ColumnInfo(name = "class_name")
    private String className;
}

