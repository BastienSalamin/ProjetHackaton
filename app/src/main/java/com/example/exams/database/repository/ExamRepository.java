package com.example.exams.database.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.exams.database.entity.ExamEntity;
import com.example.exams.database.firebase.ExamLiveData;
import com.example.exams.database.firebase.ExamsListLiveData;
import com.example.exams.database.firebase.ExamsStudentsLiveData;
import com.example.exams.database.pojo.ExamWithStudents;
import com.example.exams.util.OnAsyncEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class ExamRepository {
    private static ExamRepository instance;

    private ExamRepository(){}

    public static ExamRepository getInstance() {
        if (instance == null) {
            synchronized (ExamRepository.class) {
                if (instance == null) {
                    instance = new ExamRepository();
                }
            }
        }
        return instance;
    }
    public LiveData<List<ExamEntity>> getAllExams(Application application){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("exams");
        return new ExamsListLiveData(reference);
    }

    public LiveData<ExamEntity> getExam(final String examID, Application application) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("exams").child(examID);
        return new ExamLiveData(reference);
    }

    public LiveData<List<ExamWithStudents>> getStudentsIdFromExam(final String examId, Application application) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("examsStudents").child(examId);
        return new ExamsStudentsLiveData(reference);
    }

    public void insert(final ExamWithStudents exam, OnAsyncEventListener callback, Application application){
        // Insertion dans la table Exams
        DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference("exams");
        String key = dbReference.push().getKey();
        FirebaseDatabase.getInstance().getReference("exams").child(key).setValue(exam.exam, (databaseErr, databaseRef) -> {
            if(databaseErr != null) {
                callback.onFailure(databaseErr.toException());
            } else {
                callback.onSuccess();
            }
        });
        // Insertion dans la table ExamsStudents
        FirebaseDatabase.getInstance().getReference("examsStudents").child(key).setValue(exam, (databaseError, databaseReference) -> {
            if(databaseError != null) {
                callback.onFailure(databaseError.toException());
            } else {
                callback.onSuccess();
            }
        });
    }

    public void update(final ExamWithStudents exam, OnAsyncEventListener callback, Application application){
        // Modification dans la table Exams
        FirebaseDatabase.getInstance().getReference("exams").child(exam.exam.getIdExam()).updateChildren(exam.exam.toMap(), (databaseErr, databaseRef) -> {
            if(databaseErr != null) {
                callback.onFailure(databaseErr.toException());
            } else {
                callback.onSuccess();
            }
        });
        // Modification dans la table ExamsStudents
        FirebaseDatabase.getInstance().getReference("examsStudents").child(exam.exam.getIdExam()).updateChildren(exam.toMap(), (databaseError, databaseReference) -> {
            if(databaseError != null) {
                callback.onFailure(databaseError.toException());
            } else {
                callback.onSuccess();
            }
        });
    }

    public void delete(final ExamWithStudents exam, OnAsyncEventListener callback, Application application){
        // Suppression dans la table Exams
        FirebaseDatabase.getInstance().getReference("exams").child(exam.exam.getIdExam()).removeValue((databaseErr, databaseRef) -> {
            if(databaseErr != null) {
                callback.onFailure(databaseErr.toException());
            } else {
                callback.onSuccess();
            }
        });
        // Suppression dans la table ExamsStudents
        FirebaseDatabase.getInstance().getReference("examsStudents").child(exam.exam.getIdExam()).removeValue((databaseError, databaseReference) -> {
            if(databaseError != null) {
                callback.onFailure(databaseError.toException());
            } else {
                callback.onSuccess();
            }
        });
    }
}
