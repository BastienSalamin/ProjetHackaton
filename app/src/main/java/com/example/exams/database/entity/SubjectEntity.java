package com.example.exams.database.entity;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class SubjectEntity {
    private String id_Subject;

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
        result.put("subjectName", subjectName);
        return result;
    }
}
