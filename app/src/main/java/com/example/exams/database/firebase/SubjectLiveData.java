package com.example.exams.database.firebase;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.exams.database.entity.SubjectEntity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SubjectLiveData extends LiveData<List<SubjectEntity>> {
    private static final String TAG = "SubjectLiveData";

    private final DatabaseReference reference;
    private final SubjectLiveData.MyValueEventListener listener = new SubjectLiveData.MyValueEventListener();

    public SubjectLiveData(DatabaseReference ref) {
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
            setValue(toSubjectList(dataSnapshot));
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            Log.e(TAG, "Can't listen to query " + reference, databaseError.toException());
        }
    }

    private List<SubjectEntity> toSubjectList(DataSnapshot snapshot) {
        List<SubjectEntity> subjectEntityList = new ArrayList<>();
        for(DataSnapshot childSnapshot : snapshot.getChildren()) {
            SubjectEntity subject = childSnapshot.getValue(SubjectEntity.class);
            subject.setId_Subject(childSnapshot.getKey());
            subjectEntityList.add(subject);
        }
        return subjectEntityList;
    }
}
