package com.example.exams.database.entity;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "Student")

public class StudentEntity {

    public StudentEntity(String name,String surname){

        this.name = name;
        this.surname = surname;

    }


    @PrimaryKey(autoGenerate = true)
    private int id_Student;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "surname")
    private String surname;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }



}
