package com.example.exams.database.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.exams.BaseApp;
import com.example.exams.database.async.student.CreateStudent;
import com.example.exams.database.async.student.DeleteStudent;
import com.example.exams.database.async.student.UpdateStudent;
import com.example.exams.database.entity.StudentEntity;
import com.example.exams.util.OnAsyncEventListener;

import java.util.List;

public class StudentRepository {
    private static StudentRepository instance;

    private StudentRepository() {
    }

    public static StudentRepository getInstance() {
        if (instance == null) {
            synchronized (StudentRepository.class) {
                if (instance == null) {
                    instance = new StudentRepository();
                }
            }
        }
        return instance;
    }

    public LiveData<List<StudentEntity>> getAllStudents(Application application) {
        return ((BaseApp) application).getDatabase().studentDao().getAll();
    }

    public LiveData<StudentEntity> getStudent(final String studentId, Application application) {
        return ((BaseApp) application).getDatabase().studentDao().getById(studentId);
    }

    public void insert(final StudentEntity student, OnAsyncEventListener callback, Application application) {
        new CreateStudent(application, callback).execute(student);
    }

    public void update (final StudentEntity student, OnAsyncEventListener callback, Application application) {
        new UpdateStudent(application, callback).execute(student);
    }

    public void delete (final StudentEntity student, OnAsyncEventListener callback, Application application) {
        new DeleteStudent(application, callback).execute(student);
    }
}
