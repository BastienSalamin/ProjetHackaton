package com.example.exams.viewmodel.subject;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.example.exams.database.entity.SubjectEntity;
import com.example.exams.database.repository.SubjectRepository;

import java.util.List;

public class SubjectsListViewModel extends AndroidViewModel {
    private Application application;

    private SubjectRepository repository;

    private final MediatorLiveData<List<SubjectEntity>> observableSubjects;

    public SubjectsListViewModel(@NonNull Application application) {
        super(application);

        this.application = application;

        this.repository = SubjectRepository.getInstance();

        observableSubjects = new MediatorLiveData<>();
        observableSubjects.setValue(null);

        LiveData<List<SubjectEntity>> subjects = repository.getAllSubjects(application);
        observableSubjects.addSource(subjects, observableSubjects::setValue);
    }

    public LiveData<List<SubjectEntity>> getAllSubjects() {
        return observableSubjects;
    }

}
