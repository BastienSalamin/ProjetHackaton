package com.example.exams.database.entity;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class StudentEntity {
    private String idStudent;

    private String className;

    private String surname;

    private String name;

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
        result.put("className", className);
        result.put("surname", surname);
        result.put("name", name);
        return result;
    }
}
