package com.example.exams.database.dao;

import android.database.sqlite.SQLiteConstraintException;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.exams.database.CrossRef.ExamsStudents;

import java.util.List;

@Dao
public interface ExamsStudentsDao {
    @Insert
    long insert(ExamsStudents examsStudents) throws SQLiteConstraintException;

    @Query("DELETE FROM ExamsStudents")
    void deleteAll();

    @Query("DELETE FROM ExamsStudents WHERE idExam = :idExam")
    void deleteExam(String idExam);

    @Query("DELETE FROM ExamsStudents WHERE idStudent = :idStudent")
    void deleteStudent(String idStudent);

    @Query("SELECT * FROM ExamsStudents WHERE idExam = :idExam")
    LiveData<List<ExamsStudents>> getStudentsIdFromExam(String idExam);
}
