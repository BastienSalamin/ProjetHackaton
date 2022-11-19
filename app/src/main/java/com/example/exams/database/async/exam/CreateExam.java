package com.example.exams.database.async.exam;

import android.app.Application;
import android.os.AsyncTask;

import com.example.exams.BaseApp;
import com.example.exams.database.CrossRef.ExamsStudents;
import com.example.exams.database.entity.ExamEntity;
import com.example.exams.database.entity.StudentEntity;
import com.example.exams.database.pojo.ExamWithStudents;
import com.example.exams.util.OnAsyncEventListener;

public class CreateExam extends AsyncTask<ExamWithStudents, Void, Void> {
    private Application application;
    private OnAsyncEventListener callback;
    private Exception exception;

    public CreateExam(Application application, OnAsyncEventListener callback){
        this.application = application;
        this.callback = callback;
    }

    @Override
    protected Void doInBackground(ExamWithStudents... params) {
        try {
            for (ExamWithStudents exam : params){
                long id = ((BaseApp) application).getDatabase().examDao().insert(exam.exam);
                for (StudentEntity student : exam.students) {
                    ExamsStudents examsStudents = new ExamsStudents(id, student.getIdStudent());
                    ((BaseApp) application).getDatabase().examsStudentsDao().insert(examsStudents);
                }
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
