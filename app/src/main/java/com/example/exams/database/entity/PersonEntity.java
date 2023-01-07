package com.example.exams.database.entity;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class PersonEntity {

    public PersonEntity(){

    }

    public PersonEntity(String className, String surname, String name, String status){

        this.className = className;
        this.surname = surname;
        this.name = name;
        this.status = status;
    }

    private String idPerson;
    private String className;
    private String surname;
    private String name;
    private String status;

    @Exclude

    public String getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(String idPerson) {
        this.idPerson = idPerson;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("className", className);
        result.put("surname", surname);
        result.put("name", name);
        result.put("status", status);
        return result;
    }
}
