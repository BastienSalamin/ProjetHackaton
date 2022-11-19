package com.example.exams.database.dao;

import android.database.sqlite.SQLiteConstraintException;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.exams.database.CrossRef.ExamsStudents;
import com.example.exams.database.entity.StudentEntity;
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

    @Query("DELETE FROM ExamsStudents WHERE idExam = :idExam")
    void deleteExam(String idExam);

    @Query("DELETE FROM ExamsStudents WHERE idStudent = :idStudent")
    void deleteStudent(String idStudent);

    @Query("SELECT * FROM ExamsStudents WHERE idExam = :idExam")
    LiveData<List<ExamsStudents>> getStudentsIdFromExam(String idExam);
}
