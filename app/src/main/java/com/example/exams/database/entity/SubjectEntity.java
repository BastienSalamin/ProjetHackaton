package com.example.exams.database.entity;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Room")
public class SubjectEntity {

    @PrimaryKey
    public int id_Subject;

    @ColumnInfo(name = "Subject_name")
    public String subjectName;
}
