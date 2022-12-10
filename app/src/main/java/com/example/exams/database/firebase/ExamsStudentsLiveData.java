package com.example.exams.database.firebase;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.exams.database.pojo.ExamWithStudents;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ExamsStudentsLiveData extends LiveData<List<ExamWithStudents>> {
    private static final String TAG = "ExamsStudentsLiveData";

    private final DatabaseReference reference;
    private final ExamsStudentsLiveData.MyValueEventListener listener = new ExamsStudentsLiveData.MyValueEventListener();

    public ExamsStudentsLiveData(DatabaseReference ref) {
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
            setValue(toExamsStudentsList(dataSnapshot));
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            Log.e(TAG, "Can't listen to query " + reference, databaseError.toException());
        }
    }

    private List<ExamWithStudents> toExamsStudentsList(DataSnapshot snapshot) {
        List<ExamWithStudents> examWithStudentsList = new ArrayList<>();
        for(DataSnapshot childSnapshot : snapshot.getChildren()) {
            ExamWithStudents exam = childSnapshot.getValue(ExamWithStudents.class);
            exam.exam.setIdExam(childSnapshot.getKey());
            examWithStudentsList.add(exam);
        }
        return examWithStudentsList;
    }
}
