package com.example.exams.database.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.exams.BaseApp;
import com.example.exams.database.async.student.CreateStudent;
import com.example.exams.database.async.student.DeleteStudent;
import com.example.exams.database.async.student.UpdateStudent;
import com.example.exams.database.entity.StudentEntity;
import com.example.exams.database.firebase.RoomLiveData;
import com.example.exams.database.firebase.StudentLiveData;
import com.example.exams.database.firebase.StudentsListLiveData;
import com.example.exams.util.OnAsyncEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("students");
        return new StudentsListLiveData(reference);
    }

    public LiveData<StudentEntity> getStudent(final String studentId, Application application) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("students").child(studentId);
        return new StudentLiveData(reference);
    }

    public void insert(final StudentEntity student, OnAsyncEventListener callback) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("students");
        String key = reference.push().getKey();
        FirebaseDatabase.getInstance().getReference("students").child(key).setValue(student, (databaseError, databaseReference) -> {
            if(databaseError != null) {
                callback.onFailure(databaseError.toException());
            } else {
                callback.onSuccess();
            }
        });
    }

    public void update (final StudentEntity student, OnAsyncEventListener callback) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("students");
        reference.child(student.getIdStudent()).updateChildren(student.toMap(),(databaseError, databaseReference) -> {
            if (databaseError != null) {
                callback.onFailure(databaseError.toException());
            } else {
                callback.onSuccess();
            }
        });
    }

    public void delete (final StudentEntity student, OnAsyncEventListener callback, Application application) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("students");
        reference.child(student.getIdStudent()).removeValue((databaseError, databaseReference) -> {
            if (databaseError != null) {
                callback.onFailure(databaseError.toException());
            } else {
                callback.onSuccess();
            }
        });
    }
}
