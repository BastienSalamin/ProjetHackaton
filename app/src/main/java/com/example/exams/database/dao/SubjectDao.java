package com.example.exams.database.dao;

import android.database.sqlite.SQLiteConstraintException;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.exams.database.entity.SubjectEntity;

import java.util.List;

public interface SubjectDao {

    @Query("SELECT * FROM SubjectEntity WHERE id_subject = :id")
    LiveData<SubjectEntity> getById(String id);

    @Query("SELECT * FROM subject")
    LiveData<List<SubjectEntity>> getAll();

    @Transaction
    @Query("SELECT * FROM SubjectEntity WHERE id_subject != :id")
    LiveData<List<SubjectEntity>> getOtherClientsWithAccounts(String id);

    @Insert
    long insert(SubjectEntity subjectEntity) throws SQLiteConstraintException;

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<SubjectEntity> subjectEntities);

    @Update
    void update(SubjectEntity subjectEntity);

    @Delete
    void delete(SubjectEntity subjectEntity);

    @Query("DELETE FROM subject")
    void deleteAll();

}
