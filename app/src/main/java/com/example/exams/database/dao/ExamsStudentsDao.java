package com.example.exams.database.dao;

import androidx.room.Query;
import androidx.room.Transaction;

import com.example.exams.database.pojo.ExamWithStudents;
import com.example.exams.database.pojo.StudentWithExams;

import java.util.List;

public interface ExamsStudentsDao {
    @Transaction
    @Query("SELECT * FROM Exam")
    public List<ExamWithStudents> getExamWithStudents();

    @Transaction
    @Query("SELECT * FROM STUDENT")
    public List<StudentWithExams> getStudentWithExams();
}
