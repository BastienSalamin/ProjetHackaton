package com.example.exams.database.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.exams.BaseApp;
import com.example.exams.database.async.subject.CreateSubject;
import com.example.exams.database.async.subject.DeleteSubject;
import com.example.exams.database.async.subject.UpdateSubject;
import com.example.exams.database.entity.SubjectEntity;
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
        
        return ((BaseApp) application).getDatabase().subjectDao().getAll();
    }

    public void insert(final SubjectEntity sub, OnAsyncEventListener callback, Application application){
        new CreateSubject(application, callback).execute(sub);
    }

    public void update(final SubjectEntity sub, OnAsyncEventListener callback, Application application){
        new UpdateSubject(application, callback).execute(sub);
    }

    public void delete(final SubjectEntity sub, OnAsyncEventListener callback, Application application){
        new DeleteSubject(application, callback).execute(sub);
    }
}
