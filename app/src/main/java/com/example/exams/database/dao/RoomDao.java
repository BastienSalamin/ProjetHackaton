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

import com.example.exams.database.entity.RoomEntity;

import java.util.List;

@Dao
public interface RoomDao {

    @Query("SELECT * FROM room WHERE id_Room = :id")
    LiveData<RoomEntity> getById(String id);

    @Query("SELECT * FROM room")
    LiveData<List<RoomEntity>> getAll();

    @Transaction
    @Query("SELECT * FROM room WHERE id_Room != :id")
    LiveData<List<RoomEntity>> getOtherClientsWithAccounts(String id);

    @Insert
    long insert(RoomEntity roomEntity) throws SQLiteConstraintException;

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<RoomEntity> roomEntities);

    @Update
    void update(RoomEntity roomEntity);

    @Delete
    void delete(RoomEntity roomEntity);

    @Query("DELETE FROM room")
    void deleteAll();

}
