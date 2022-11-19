package com.example.exams;

import android.app.Application;

import com.example.exams.database.AppDatabase;
import com.example.exams.database.repository.ExamRepository;
import com.example.exams.database.repository.StudentRepository;
import static com.example.exams.database.AppDatabase.initializeDemoData;


public class BaseApp extends Application {
    @Override
    public void onCreate() {
        initializeDemoData(AppDatabase.getInstance(this));
        super.onCreate();
    }

    public AppDatabase getDatabase() {
        return AppDatabase.getInstance(this);
    }

    public StudentRepository getStudentRepository() {
        return StudentRepository.getInstance();
    }

    public ExamRepository getExamRepository() {
        return ExamRepository.getInstance();
    }
}
