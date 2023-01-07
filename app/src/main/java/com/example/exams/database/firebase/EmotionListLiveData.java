package com.example.exams.database.firebase;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.exams.database.entity.EmotionEntity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EmotionListLiveData extends LiveData<List<EmotionEntity>> {
    private static final String TAG = "EmotionListLiveData";

    private final DatabaseReference reference;
    private final EmotionListLiveData.MyValueEventListener listener = new EmotionListLiveData.MyValueEventListener();

    public EmotionListLiveData(DatabaseReference ref) {
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
            setValue(toEmotionList(dataSnapshot));
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            Log.e(TAG, "Can't listen to query " + reference, databaseError.toException());
        }
    }

    private List<EmotionEntity> toEmotionList(DataSnapshot snapshot) {
        List<EmotionEntity> emotionEntityList = new ArrayList<>();
        for(DataSnapshot childSnapshot : snapshot.getChildren()) {
            EmotionEntity emotion = childSnapshot.getValue(EmotionEntity.class);
            emotion.setIdEmotion(childSnapshot.getKey());
            emotionEntityList.add(emotion);
        }
        return emotionEntityList;
    }
}
