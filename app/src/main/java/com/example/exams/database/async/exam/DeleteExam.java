package com.example.exams.database.async.exam;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.AsyncTask;

import com.example.exams.BaseApp;
import com.example.exams.database.entity.ExamEntity;
import com.example.exams.database.entity.StudentEntity;
import com.example.exams.util.OnAsyncEventListener;

public class DeleteExam extends AsyncTask<ExamEntity, Void, Void> {
    private Application application;
    private OnAsyncEventListener callback;
    private Exception exception;

    public DeleteExam(Application application, OnAsyncEventListener callback){
        this.application = application;
        this.callback = callback;
    }

    @Override
    public void onPostExecute(Void unused) {
        if (callback != null) {
            if (exception == null) {
                callback.onSuccess();
            } else {
                callback.onFailure(exception);
            }
        }
    }

    @Override
    protected Void doInBackground(ExamEntity... params){
        try {
            for(@SuppressLint("SuspiciousIndentation") ExamEntity exam : params)
            ((BaseApp)application).getDatabase().examDao().delete(exam);
        }catch (Exception e){
            exception = e;
        }
        return null;
    }
}
