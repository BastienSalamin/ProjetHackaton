package com.example.exams.database.firebase;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RoomLiveData extends LiveData<List<RoomEntity>> {
    private static final String TAG = "RoomLiveData";

    private final DatabaseReference reference;
    private final RoomLiveData.MyValueEventListener listener = new RoomLiveData.MyValueEventListener();

    public RoomLiveData(DatabaseReference ref) {
        reference = ref;
    }

    @Override
    protected void onActive() {
        Log.d(TAG, "onActive");
        reference.addValueEventListener(listener);
    }

    @Override
    protected void onInactive() {
        Log.d(TAG, "onInactive");
    }

    private class MyValueEventListener implements ValueEventListener {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            setValue(toRoomList(dataSnapshot));
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            Log.e(TAG, "Can't listen to query " + reference, databaseError.toException());
        }
    }

    private List<RoomEntity> toRoomList(DataSnapshot snapshot) {
        List<RoomEntity> roomEntityList = new ArrayList<>();
        for(DataSnapshot childSnapshot : snapshot.getChildren()) {
            RoomEntity room = childSnapshot.getValue(RoomEntity.class);
            room.setId_Room(childSnapshot.getKey());
            roomEntityList.add(room);
        }
        return roomEntityList;
    }
}
