package com.example.exams.database.async.student;

import android.app.Application;
import android.os.AsyncTask;

import com.example.exams.BaseApp;
import com.example.exams.database.entity.StudentEntity;
import com.example.exams.util.OnAsyncEventListener;

public class DeleteStudent extends AsyncTask<StudentEntity, Void, Void> {
    private Application application;
    private OnAsyncEventListener callback;
    private Exception exception;

    public DeleteStudent(Application application, OnAsyncEventListener callback) {
        this.application = application;
        this.callback = callback;
    }

    @Override
    protected void onPostExecute(Void unused) {
        if (callback != null) {
            if (exception == null) {
                callback.onSuccess();
            } else {
                callback.onFailure(exception);
            }
        }
    }

    @Override
    protected Void doInBackground(StudentEntity... params) {
        try {
            for(StudentEntity student : params)
                ((BaseApp) application).getDatabase().studentDao().delete(student);
        } catch (Exception e) {
            exception = e;
        }
        return null;
    }
}
