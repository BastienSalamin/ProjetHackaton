package com.example.exams.database.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.exams.database.entity.RoomEntity;
import com.example.exams.database.firebase.RoomLiveData;
import com.example.exams.util.OnAsyncEventListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("rooms");
        return new RoomLiveData(reference);
    }

    public void insert(final RoomEntity room, final OnAsyncEventListener callback){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("rooms");

        reference.push().setValue(room, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError != null) {
                    callback.onFailure(databaseError.toException());
                } else {
                    callback.onSuccess();
                }
            }
        });

       /* DatabaseReference reference = FirebaseDatabase.getInstance().getReference("rooms");
        String key = reference.push().getKey();
        FirebaseDatabase.getInstance().getReference("rooms").child(key).setValue(room, (databaseError, databaseReference) -> {
            if(databaseError != null) {
                callback.onFailure(databaseError.toException());
            } else {
                callback.onSuccess();
            }
        });*/
    }

    public void update(final RoomEntity room, OnAsyncEventListener callback) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("rooms");
        reference.child(room.getId_Room()).updateChildren(room.toMap(),(databaseError, databaseReference) -> {
            if (databaseError != null) {
                callback.onFailure(databaseError.toException());
            } else {
                callback.onSuccess();
            }
        });
    }

    public void delete(final RoomEntity room, OnAsyncEventListener callback){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("rooms");
        reference.child(room.getId_Room()).removeValue((databaseError, databaseReference) -> {
            if (databaseError != null) {
                callback.onFailure(databaseError.toException());
            } else {
                callback.onSuccess();
            }
        });
    }
}
