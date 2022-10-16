package com.example.exams;

import android.app.Application;

import com.example.exams.database.AppDatabase;

public class BaseApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    public AppDatabase getDatabase() {
        return AppDatabase.getInstance(this);
    }
}
