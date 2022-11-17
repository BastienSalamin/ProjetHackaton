package com.example.exams.database.async.exam;

import android.app.Application;
import android.os.AsyncTask;

import com.example.exams.BaseApp;
import com.example.exams.database.entity.ExamEntity;
import com.example.exams.util.OnAsyncEventListener;

public class CreateExam extends AsyncTask<ExamEntity, Void, Void> {
    private Application application;
    private OnAsyncEventListener callback;
    private Exception exception;

    public CreateExam(Application application, OnAsyncEventListener callback){
        this.application = application;
        this.callback = callback;
    }

    @Override
    protected Void doInBackground(ExamEntity... params) {
        try {
            for (ExamEntity exam : params){
                ((BaseApp) application).getDatabase().examDao().insert(exam);
            }
        }catch (Exception e){
            exception = e;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void unused){
        if(callback != null){
            if(exception == null){
                callback.onSuccess();
            }else {
                callback.onFailure(exception);
            }
        }
    }
}
