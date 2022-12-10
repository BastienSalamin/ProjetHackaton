package com.example.exams.database.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.exams.database.entity.SubjectEntity;
import com.example.exams.database.firebase.SubjectLiveData;
import com.example.exams.util.OnAsyncEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class SubjectRepository {
    private static SubjectRepository instance;

    private SubjectRepository(){}

    public static SubjectRepository getInstance() {
        if (instance == null) {
            synchronized (SubjectRepository.class) {
                if (instance == null) {
                    instance = new SubjectRepository();
                }
            }
        }
        return instance;
    }

    public LiveData<List<SubjectEntity>> getAllSubjects(Application application) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("subjects");
        return new SubjectLiveData(reference);
    }

    public void insert(final SubjectEntity sub, OnAsyncEventListener callback, Application application){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("subjects");
        String key = reference.push().getKey();
        FirebaseDatabase.getInstance().getReference("subjects").child(key).setValue(sub, (databaseError, databaseReference) -> {
            if(databaseError != null) {
                callback.onFailure(databaseError.toException());
            } else {
                callback.onSuccess();
            }
        });
    }

    public void update(final SubjectEntity sub, OnAsyncEventListener callback, Application application){
        FirebaseDatabase.getInstance().getReference("subjects").child(sub.getId_Subject()).updateChildren(sub.toMap(), (databaseError, databaseReference) -> {
            if(databaseError != null) {
                callback.onFailure(databaseError.toException());
            } else {
                callback.onSuccess();
            }
        });
    }

    public void delete(final SubjectEntity sub, OnAsyncEventListener callback, Application application){
        FirebaseDatabase.getInstance().getReference("subjects").child(sub.getId_Subject()).removeValue((databaseError, databaseReference) -> {
            if(databaseError != null) {
                callback.onFailure(databaseError.toException());
            } else {
                callback.onSuccess();
            }
        });
    }
}
