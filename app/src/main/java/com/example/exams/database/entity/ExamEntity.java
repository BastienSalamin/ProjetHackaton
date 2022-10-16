package com.example.exams.database.entity;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "exam")
public class ExamEntity {

    public ExamEntity(Date date, int duration, int numberStudents){
        this.date = date;
        this.duration = duration;
        this.numberStudents = numberStudents;
    }

    @PrimaryKey
    private int id_Exam;

    @ColumnInfo(name = "date")
    private Date date;

    @ColumnInfo(name = "duration")
    private int duration;

    @ColumnInfo(name = "Nbre_of_Students")
    private int numberStudents;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getNumberStudents() {
        return numberStudents;
    }

    public void setNumberStudents(int numberStudents) {
        this.numberStudents = numberStudents;
    }



}


