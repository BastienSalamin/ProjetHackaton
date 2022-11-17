package com.example.exams.database.async.subject;

import android.app.Application;
import android.os.AsyncTask;

import com.example.exams.BaseApp;
import com.example.exams.database.entity.SubjectEntity;
import com.example.exams.util.OnAsyncEventListener;

public class UpdateSubject extends AsyncTask<SubjectEntity,Void,Void> {
    private Application application;
    private OnAsyncEventListener callback;
    private Exception exception;

    public UpdateSubject(Application application, OnAsyncEventListener callback){
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
    protected Void doInBackground(SubjectEntity... params){
        try {
            for(SubjectEntity sub : params)
            ((BaseApp)application).getDatabase().subjectDao().update(sub);
        }catch (Exception e){
            exception = e;
        }
        return null;
    }

}
