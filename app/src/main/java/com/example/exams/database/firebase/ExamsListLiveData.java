package com.example.exams.database.firebase;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.exams.database.entity.ExamEntity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ExamsListLiveData extends LiveData<List<ExamEntity>> {
    private static final String TAG = "ExamsStudentsLiveData";

    private final DatabaseReference reference;
    private final ExamsListLiveData.MyValueEventListener listener = new ExamsListLiveData.MyValueEventListener();

    public ExamsListLiveData(DatabaseReference ref) {
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
            setValue(toExamList(dataSnapshot));
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            Log.e(TAG, "Can't listen to query " + reference, databaseError.toException());
        }
    }

    private List<ExamEntity> toExamList(DataSnapshot snapshot) {
        List<ExamEntity> examList = new ArrayList<>();
        for(DataSnapshot childSnapshot : snapshot.getChildren()) {
            ExamEntity exam = childSnapshot.getValue(ExamEntity.class);
            exam.setIdExam(childSnapshot.getKey());
            examList.add(exam);
        }
        return examList;
    }
}
