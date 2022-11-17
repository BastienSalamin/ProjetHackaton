package com.example.exams.util;

import com.example.exams.database.entity.ExamEntity;

public interface OnAsyncEventListener {
    void onSuccess();
    void onFailure(Exception e);

    void onPostExecute(Void unused);

    Void doInBackground(ExamEntity... params);
}
