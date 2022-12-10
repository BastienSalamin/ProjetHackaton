package com.example.exams.database.entity;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

// @Entity(tableName = "Student")
public class StudentEntity {
    // @PrimaryKey(autoGenerate = true)
    private String idStudent;

    // @ColumnInfo(name = "Class")
    private String className;

    // @ColumnInfo(name = "Surname")
    private String surname;

    // @ColumnInfo(name = "Name")
    private String name;

    // @Ignore
    public StudentEntity() {
    }

    public StudentEntity(String className, String surname, String name){
        this.className = className;
        this.surname = surname;
        this.name = name;
    }

    @Exclude
    public String getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(String idStudent) {
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

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("classname", className);
        result.put("surname", surname);
        result.put("name", name);
        return result;
    }
}
