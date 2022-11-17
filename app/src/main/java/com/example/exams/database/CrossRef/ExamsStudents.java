package com.example.exams.database.CrossRef;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(primaryKeys = {"id_Exam", "idStudent"})
public class ExamsStudents {

    private long id_Exam;
    private long idStudent;

    public long getId_Exam() {
        return id_Exam;
    }

    public void setId_Exam(long id_Exam) {
        this.id_Exam = id_Exam;
    }

    public long getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(long idStudent) {
        this.idStudent = idStudent;
    }
}
