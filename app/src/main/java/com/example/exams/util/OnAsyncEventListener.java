package com.example.exams.util;

public interface OnAsyncEventListener {
    void onSuccess();
    void onFailure(Exception e);
}
