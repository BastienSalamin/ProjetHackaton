package com.example.exams;

import android.app.Application;


public class BaseApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    public StudentRepository getStudentRepository() {
        return StudentRepository.getInstance();
    }

    public ExamRepository getExamRepository() {
        return ExamRepository.getInstance();
    }
}
