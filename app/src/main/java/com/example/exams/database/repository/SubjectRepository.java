package com.example.exams.database.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.exams.database.entity.SubjectEntity;
import com.example.exams.database.pojo.SubjectWithEmotions;
import com.example.exams.util.OnAsyncEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class SubjectRepository {
    private static SubjectRepository instance;

    private ExamRepository(){}

    public static SubjectRepository getInstance(){
        if(instance == null){
            synchronized (SubjectRepository.class){
                if(instance == null){
                    instance = new SubjectRepository();
                }
            }
        }
        return instance;
    }

    public LiveData<List<SubjectEntity>> getAllSubjects(Application application){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("subjects");
        return new SubjectsListLiveData(reference);
    }

    public LiveData<SubjectEntity> getSubject(final String subjectId, Application application) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("subjects").child(subjectId);
        return new SubjectLiveData(reference);
    }

    public LiveData<SubjectWithEmotions> getemotionsIdFromSubject(final String subjectId, Application application) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("subjectsEmotions").child(subjectId);
        return new SubjectsEmotionsLiveData(reference);
    }

    public void insert(final SubjectWithEmotions subject, OnAsyncEventListener callback, Application application){
        // Insertion dans la table Exams
        DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference("subjects");
        String key = dbReference.push().getKey();
        FirebaseDatabase.getInstance().getReference("subjects").child(key).setValue(subject.subject, (databaseErr, databaseRef) -> {
            if(databaseErr != null) {
                callback.onFailure(databaseErr.toException());
            } else {
                callback.onSuccess();
            }
        });
        // Insertion dans la table ExamsStudents
        FirebaseDatabase.getInstance().getReference("subjectsEmotions").child(key).setValue(subject.toMap(), (databaseError, databaseReference) -> {
            if(databaseError != null) {
                callback.onFailure(databaseError.toException());
            } else {
                callback.onSuccess();
            }
        });
    }

    public void update(final SubjectWithEmotions subject, OnAsyncEventListener callback, Application application){
        // Modification dans la table Exams
        FirebaseDatabase.getInstance().getReference("subjects").child(subject.subject.getIdSubject()).updateChildren(subject.subject.toMap(), (databaseErr, databaseRef) -> {
            if(databaseErr != null) {
                callback.onFailure(databaseErr.toException());
            } else {
                callback.onSuccess();
            }
        });
        // Modification dans la table ExamsStudents
        FirebaseDatabase.getInstance().getReference("subjectsEmotions").child(subject.getId()).setValue(subject.toMap(), (databaseError, databaseReference) -> {
            if(databaseError != null) {
                callback.onFailure(databaseError.toException());
            } else {
                callback.onSuccess();
            }
        });

        public void delete(final SubjectWithEmotions subject, OnAsyncEventListener callback, Application application){
            // Suppression dans la table ExamsStudents
            FirebaseDatabase.getInstance().getReference("subjectsEmotions").child(subject.getId()).removeValue((databaseError, databaseReference) -> {
                if(databaseError != null) {
                    callback.onFailure(databaseError.toException());
                } else {
                    callback.onSuccess();
                }
            });
            // Suppression dans la table Exams
            FirebaseDatabase.getInstance().getReference("subjects").child(subject.getId()).removeValue((databaseErr, databaseRef) -> {
                if(databaseErr != null) {
                    callback.onFailure(databaseErr.toException());
                } else {
                    callback.onSuccess();
                }
            });
        }
    }
}
