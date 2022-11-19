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

import com.example.exams.database.entity.SubjectEntity;

import java.util.List;

@Dao
public interface SubjectDao {

    @Query("SELECT * FROM Subject WHERE id_subject = :id")
    LiveData<SubjectEntity> getById(String id);

    @Query("SELECT * FROM Subject")
    LiveData<List<SubjectEntity>> getAll();

    @Insert
    long insert(SubjectEntity subjectEntity) throws SQLiteConstraintException;

    @Update
    void update(SubjectEntity subjectEntity);

    @Delete
    void delete(SubjectEntity subjectEntity);

    @Query("DELETE FROM subject")
    void deleteAll();

}
