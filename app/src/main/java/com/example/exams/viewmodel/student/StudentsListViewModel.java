package com.example.exams.viewmodel.student;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.example.exams.database.entity.StudentEntity;
import com.example.exams.database.repository.StudentRepository;
import java.util.List;

public class StudentsListViewModel extends AndroidViewModel {
    private Application application;

    private StudentRepository repository;

    private final MediatorLiveData<List<StudentEntity>> observableStudents;

    public StudentsListViewModel(@NonNull Application application) {
        super(application);

        this.application = application;

        this.repository = StudentRepository.getInstance();

        observableStudents = new MediatorLiveData<>();
        observableStudents.setValue(null);

        LiveData<List<StudentEntity>> students = repository.getAllStudents(application);
        observableStudents.addSource(students, observableStudents::setValue);
    }

    public LiveData<List<StudentEntity>> getAllStudents() {
        return observableStudents;
    }

}
