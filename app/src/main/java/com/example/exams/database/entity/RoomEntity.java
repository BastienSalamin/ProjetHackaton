package com.example.exams.database.entity;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

// @Entity(tableName = "Room")
public class RoomEntity {
    // @PrimaryKey(autoGenerate = true)
    private String id_Room;

    // @ColumnInfo(name = "room_name")
    private String roomName;

    // @Ignore
    public RoomEntity() {

    }

    public RoomEntity(String roomName){
        this.roomName = roomName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    @Exclude
    public String getId_Room() {
        return id_Room;
    }

    public void setId_Room(String id_Room) {
        this.id_Room = id_Room;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("roomName", roomName);
        return result;
    }
}
