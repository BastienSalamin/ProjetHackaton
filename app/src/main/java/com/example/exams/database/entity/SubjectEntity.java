package com.example.exams.database.entity;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Room")
public class SubjectEntity {
    public SubjectEntity(String SubjectName){
        this.subjectName = SubjectName;
    }

    @PrimaryKey(autoGenerate = true)
    private int id_Subject;

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    @ColumnInfo(name = "Subject_name")
    private String subjectName;
}
