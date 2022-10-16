package com.example.exams.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Room")
public class RoomEntity {
    @PrimaryKey
    public int id_Room;

    @ColumnInfo(name = "room_name")
    public int roomName;

}
