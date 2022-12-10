package com.example.exams.database.CrossRef;

import androidx.room.Entity;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

// @Entity(tableName = "ExamsStudents",primaryKeys = {"idExam", "idStudent"})
public class ExamsStudents {

    private long idExam;
    private long idStudent;

    public ExamsStudents(long idExam, long idStudent) {
        this.idExam = idExam;
        this.idStudent = idStudent;
    }

    @Exclude
    public long getIdExam() {
        return idExam;
    }

    public void setIdExam(long idExam) {
        this.idExam = idExam;
    }

    @Exclude
    public long getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(long idStudent) {
        this.idStudent = idStudent;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        return result;
    }
}
