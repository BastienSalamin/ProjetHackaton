package com.example.exams.database.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.exams.BaseApp;
import com.example.exams.database.async.exam.CreateExam;
import com.example.exams.database.async.exam.DeleteExam;
import com.example.exams.database.async.exam.UpdateExam;
import com.example.exams.database.entity.ExamEntity;
import com.example.exams.database.pojo.ExamWithStudents;
import com.example.exams.util.OnAsyncEventListener;

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
        return ((BaseApp) application).getDatabase().examDao().getAll();
    }

    public LiveData<ExamEntity> getExam(final String examID, Application application){
        return ((BaseApp) application).getDatabase().examDao().getById(examID);
    }

    public void insert(final ExamWithStudents exam, OnAsyncEventListener callback, Application application){
        new CreateExam(application, callback).execute(exam);
    }

    public void update(final ExamEntity exam, OnAsyncEventListener callback, Application application){
        new UpdateExam(application, callback).execute(exam);
    }

    public void delete(final ExamEntity exam, OnAsyncEventListener callback, Application application){
        new DeleteExam(application, callback).execute(exam);
    }
}
