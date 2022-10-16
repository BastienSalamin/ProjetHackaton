package com.example.exams.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "class")
public class ClassEntity{
    @PrimaryKey
    public int id_Class;

    @ColumnInfo(name = "class_name")
    public String className;
}

