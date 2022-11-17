package com.example.exams.database.entity;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Subject")
public class SubjectEntity {

    @PrimaryKey(autoGenerate = true)
    private int id_Subject;

    @ColumnInfo(name = "subject_name")
    private String subjectName;

    @Ignore
    public SubjectEntity(){

    }

    public SubjectEntity(String subjectName){
        this.subjectName= subjectName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getId_Subject() {
        return id_Subject;
    }

    public void setId_Subject(int id_Subject) {
        this.id_Subject = id_Subject;
    }
}
