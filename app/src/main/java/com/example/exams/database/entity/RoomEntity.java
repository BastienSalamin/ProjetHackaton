package com.example.exams.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Room")
public class RoomEntity {
    @PrimaryKey(autoGenerate = true)
    private int id_Room;

    public RoomEntity(int roomName){
        this.roomName = roomName;
    }

    public int getRoomName() {
        return roomName;
    }

    public void setRoomName(int roomName) {
        this.roomName = roomName;
    }

    public int getId_Room() {
        return id_Room;
    }

    public void setId_Room(int id_Room) {
        this.id_Room = id_Room;
    }

    @ColumnInfo(name = "room_name")
    private int roomName;

}
