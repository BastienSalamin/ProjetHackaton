package com.example.exams.database.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.exams.BaseApp;
import com.example.exams.database.async.student.CreateStudent;
import com.example.exams.database.async.student.DeleteStudent;
import com.example.exams.database.async.student.UpdateStudent;
import com.example.exams.database.entity.StudentEntity;
import com.example.exams.util.OnAsyncEventListener;

import java.util.List;

public class RoomRepository {
    private static RoomRepository instance;

    private RoomRepository(){}

    public static RoomRepository getInstance() {
        if (instance == null) {
            synchronized (RoomRepository.class) {
                if (instance == null) {
                    instance = new RoomRepository();
                }
            }
        }
        return instance;
    }
}
