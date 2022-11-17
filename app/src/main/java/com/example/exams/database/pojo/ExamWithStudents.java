package com.example.exams.database.pojo;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Junction;
import androidx.room.Relation;

import com.example.exams.database.CrossRef.ExamsStudents;
import com.example.exams.database.entity.ExamEntity;
import com.example.exams.database.entity.StudentEntity;

import java.util.List;

public class ExamWithStudents {
    @Embedded public ExamEntity exam;

    @Relation(
            parentColumn = "id_Exam",
            entityColumn = "idStudent",
            associateBy = @Junction(ExamsStudents.class)
    )
    public List<StudentEntity> students;
}
