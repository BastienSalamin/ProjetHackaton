package com.example.exams.database.dao;

import android.database.sqlite.SQLiteConstraintException;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.exams.database.entity.RoomEntity;
import com.example.exams.database.entity.StudentEntity;

import java.util.List;

public interface StudentDao {

    @Query("SELECT * FROM student WHERE id_Student = :id")
    LiveData<StudentEntity> getById(String id);

    @Query("SELECT * FROM student")
    LiveData<List<StudentEntity>> getAll();

    @Transaction
    @Query("SELECT * FROM Student WHERE id_Student != :id")
    LiveData<List<StudentEntity>> getOtherClientsWithAccounts(String id);

    @Insert
    long insert(StudentEntity studentEntity) throws SQLiteConstraintException;

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<StudentEntity> studentEntities);

    @Update
    void update(StudentEntity studentEntity);

    @Delete
    void delete(StudentEntity studentEntity);

    @Query("DELETE FROM student")
    void deleteAll();

}
