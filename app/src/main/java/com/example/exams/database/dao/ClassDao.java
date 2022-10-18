package com.example.exams.database.dao;

import android.database.sqlite.SQLiteConstraintException;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.exams.database.entity.ClassEntity;

import java.util.List;

public interface ClassDao {

    @Query("SELECT * FROM class WHERE id_Class = :id")
    LiveData<ClassEntity> getById(String id);

    @Query("SELECT * FROM class")
    LiveData<List<ClassEntity>> getAll();

    @Transaction
    @Query("SELECT * FROM class WHERE id_Class != :id")
    LiveData<List<ClassEntity>> getOtherClientsWithAccounts(String id);

    @Insert
    long insert(ClassEntity classEntity) throws SQLiteConstraintException;

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<ClassEntity> classEntities);

    @Update
    void update(ClassEntity classEntity);

    @Delete
    void delete(ClassEntity classEntity);

    @Query("DELETE FROM class")
    void deleteAll();

}
