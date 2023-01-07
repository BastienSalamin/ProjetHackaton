package com.example.exams.database.entity;

import com.google.firebase.database.Exclude;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SubjectEntity {
    private String id_Subject;
    private Date date;
    private int weekNbr;
    private String subjectName;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getWeekNbr() {
        return weekNbr;
    }

    public void setWeekNbr(int weekNbr) {
        this.weekNbr = weekNbr;
    }

    @Exclude
    public String getId_Subject() {
        return id_Subject;
    }

    public void setId_Subject(String id_Subject) {
        this.id_Subject = id_Subject;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("date", date);
        result.put("weekNbr", weekNbr);
        result.put("subjectName", subjectName);
        return result;
    }
}
