package com.example.exams.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Room")
public class RoomEntity {

    public RoomEntity(int roomName){
        this.roomName = roomName;
    }

    @PrimaryKey(autoGenerate = true)
    private int id_Room;

    public int getRoomName() {
        return roomName;
    }

    public void setRoomName(int roomName) {
        this.roomName = roomName;
    }

    @ColumnInfo(name = "room_name")
    private int roomName;

}
