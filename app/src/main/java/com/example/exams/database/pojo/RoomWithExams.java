package com.example.exams.database.pojo;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.exams.database.entity.ExamEntity;
import com.example.exams.database.entity.RoomEntity;

import java.util.List;

public class RoomWithExams {
    @Embedded
    public RoomEntity room;

    @Relation(parentColumn = "id_Room", entityColumn = "idRoom", entity = ExamEntity.class)
    public List<ExamEntity> exams;
}
