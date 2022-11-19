package com.example.exams.database.dao;

import android.database.sqlite.SQLiteConstraintException;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.exams.database.CrossRef.ExamsStudents;
import com.example.exams.database.pojo.ExamWithStudents;
import com.example.exams.database.pojo.StudentWithExams;

import java.util.List;

@Dao
public interface ExamsStudentsDao {
    @Insert
    long insert(ExamsStudents examsStudents) throws SQLiteConstraintException;

    @Transaction
    @Query("SELECT * FROM Exam")
    public List<ExamWithStudents> getExamWithStudents();

    @Transaction
    @Query("SELECT * FROM STUDENT")
    public List<StudentWithExams> getStudentWithExams();

    @Query("DELETE FROM ExamsStudents")
    void deleteAll();
}
