package com.example.exams.database.async.room;

import android.app.Application;
import android.os.AsyncTask;

import com.example.exams.BaseApp;
import com.example.exams.database.entity.RoomEntity;
import com.example.exams.util.OnAsyncEventListener;

public class UpdateRoom extends AsyncTask<RoomEntity, Void, Void> {
    private Application application;
    private OnAsyncEventListener callback;
    private Exception exception;

    public UpdateRoom(Application application, OnAsyncEventListener callback){
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
    protected Void doInBackground(RoomEntity... params){
        try {
            for (RoomEntity room : params){
                ((BaseApp) application).getDatabase().roomDao().update(room);
            }
        }catch (Exception e){
            exception = e;
        }
        return null;
    }
}
