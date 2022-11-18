package com.example.exams.database.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.exams.BaseApp;
import com.example.exams.database.async.exam.CreateExam;
import com.example.exams.database.async.exam.DeleteExam;
import com.example.exams.database.async.exam.UpdateExam;
import com.example.exams.database.async.room.CreateRoom;
import com.example.exams.database.async.room.DeleteRoom;
import com.example.exams.database.async.room.UpdateRoom;
import com.example.exams.database.async.student.CreateStudent;
import com.example.exams.database.async.student.DeleteStudent;
import com.example.exams.database.async.student.UpdateStudent;
import com.example.exams.database.entity.ExamEntity;
import com.example.exams.database.entity.RoomEntity;
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

    public LiveData<List<RoomEntity>> getAllRooms(Application application){
        return ((BaseApp) application).getDatabase().roomDao().getAll();
    }

    public LiveData<RoomEntity> getRoom(final String roomID, Application application){
        return ((BaseApp) application).getDatabase().roomDao().getById(roomID);
    }

    public void insert(final RoomEntity room, OnAsyncEventListener callback, Application application){
        new CreateRoom(application, callback).execute(room);
    }

    public void update(final RoomEntity room, OnAsyncEventListener callback, Application application){
        new UpdateRoom(application, callback).execute(room);
    }

    public void delete(final RoomEntity room, OnAsyncEventListener callback, Application application){
        new DeleteRoom(application, callback).execute(room);
    }
}
