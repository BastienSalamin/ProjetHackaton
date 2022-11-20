package com.example.exams.database.dao;

import android.database.sqlite.SQLiteConstraintException;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.exams.database.entity.StudentEntity;

import java.util.List;

@Dao
public interface StudentDao {

    @Query("SELECT * FROM Student WHERE idStudent = :id")
    LiveData<StudentEntity> getById(String id);

    @Query("SELECT * FROM Student")
    LiveData<List<StudentEntity>> getAll();

    @Insert
    long insert(StudentEntity studentEntity) throws SQLiteConstraintException;

    @Update
    void update(StudentEntity studentEntity);

    @Delete
    void delete(StudentEntity studentEntity);

    @Query("DELETE FROM student")
    void deleteAll();

}
