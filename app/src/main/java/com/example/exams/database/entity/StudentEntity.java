package com.example.exams.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Student")
public class StudentEntity {
    @PrimaryKey(autoGenerate = true)
    private long idStudent;

    @ColumnInfo(name = "Class")
    private String className;

    @ColumnInfo(name = "Surname")
    private String surname;

    @ColumnInfo(name = "Name")
    private String name;

    @Ignore
    public StudentEntity() {
    }

    public StudentEntity(String className, String surname, String name){
        this.className = className;
        this.surname = surname;
        this.name = name;
    }

    public long getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(long idStudent) {
        this.idStudent = idStudent;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
