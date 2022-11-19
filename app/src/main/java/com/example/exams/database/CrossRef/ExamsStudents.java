package com.example.exams.database.CrossRef;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ExamsStudents",primaryKeys = {"idExam", "idStudent"})
public class ExamsStudents {

    private long idExam;
    private long idStudent;

    public ExamsStudents(long idExam, long idStudent) {
        this.idExam = idExam;
        this.idStudent = idStudent;
    }

    public long getIdExam() {
        return idExam;
    }

    public void setIdExam(long idExam) {
        this.idExam = idExam;
    }

    public long getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(long idStudent) {
        this.idStudent = idStudent;
    }
}
