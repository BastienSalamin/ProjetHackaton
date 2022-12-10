package com.example.exams.database.firebase;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.exams.database.entity.RoomEntity;
import com.example.exams.database.entity.StudentEntity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class StudentsListLiveData extends LiveData<List<StudentEntity>> {
    private static final String TAG = "StudentsListLiveData";

    private final DatabaseReference reference;
    private final StudentsListLiveData.MyValueEventListener listener = new StudentsListLiveData.MyValueEventListener();

    public StudentsListLiveData(DatabaseReference ref) {
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
            setValue(toStudentList(dataSnapshot));
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            Log.e(TAG, "Can't listen to query " + reference, databaseError.toException());
        }
    }

    private List<StudentEntity> toStudentList(DataSnapshot snapshot) {
        List<StudentEntity> studentEntityList = new ArrayList<>();
        for(DataSnapshot childSnapshot : snapshot.getChildren()) {
            StudentEntity student = childSnapshot.getValue(StudentEntity.class);
            student.setIdStudent(childSnapshot.getKey());
            studentEntityList.add(student);
        }
        return studentEntityList;
    }
}
