package com.example.exams.database.firebase;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.exams.database.pojo.ExamWithStudents;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class ExamsStudentsLiveData extends LiveData<ExamWithStudents> {
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
            ExamWithStudents entity = dataSnapshot.getValue(ExamWithStudents.class);
            if(entity != null){
                entity.setId(dataSnapshot.getKey());
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    entity.idStudent.add(child.getKey());
                }
                setValue(entity);
            }
            else{
                System.out.println("T'as cru qu'une exception allait se lever ? ;-)");
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            Log.e(TAG, "Can't listen to query " + reference, databaseError.toException());
        }
    }
}
