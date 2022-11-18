package com.example.exams.database.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.exams.BaseApp;
import com.example.exams.database.entity.StudentEntity;
import com.example.exams.database.entity.SubjectEntity;
import com.example.exams.util.OnAsyncEventListener;

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
        return ((BaseApp) application).getDatabase().subjectDao().getAll();
    }

}
