package com.example.exams.database.dao;

import android.database.sqlite.SQLiteConstraintException;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.exams.database.entity.ExamEntity;

import java.util.List;

@Dao
public interface ExamDao {

    @Query("SELECT * FROM exam WHERE id_Exam = :id")
    LiveData<ExamEntity> getById(String id);

    @Query("SELECT * FROM exam")
    LiveData<List<ExamEntity>> getAll();

    @Transaction
    @Query("SELECT * FROM exam WHERE id_Exam != :id")
    LiveData<List<ExamEntity>> getOtherClientsWithAccounts(String id);

    @Insert
    long insert(ExamEntity examEntity) throws SQLiteConstraintException;

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<ExamEntity> examEntities);

    @Update
    void update(ExamEntity examEntity);

    @Delete
    void delete(ExamEntity examEntity);

    @Query("DELETE FROM exam")
    void deleteAll();

}
