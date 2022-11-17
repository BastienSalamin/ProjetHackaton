package com.example.exams.database.pojo;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.example.exams.database.CrossRef.ExamsStudents;
import com.example.exams.database.entity.ExamEntity;
import com.example.exams.database.entity.StudentEntity;

import java.util.List;

public class StudentWithExams {
    @Embedded public StudentEntity student;

    @Relation(
            parentColumn = "idStudent",
            entityColumn = "id_Exam",
            associateBy = @Junction(ExamsStudents.class)
    )
    public List<ExamEntity> exams;
}
