package com.example.exams.database.async.exam;

import android.app.Application;
import android.os.AsyncTask;

import com.example.exams.BaseApp;
import com.example.exams.database.entity.ExamEntity;
import com.example.exams.util.OnAsyncEventListener;

public class UpdateExam extends AsyncTask<ExamEntity, Void, Void> {
    private Application application;
    private OnAsyncEventListener callback;
    private Exception exception;

    public UpdateExam(Application application, OnAsyncEventListener callback){
        this.application = application;
        this.callback = callback;
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

    @Override
    protected Void doInBackground(ExamEntity... params) {
        try {
            for(ExamEntity exam : params){
                ((BaseApp)application).getDatabase().examDao().update(exam);
            }
        }catch (Exception e){
            exception = e;
        }
        return null;
    }
}
