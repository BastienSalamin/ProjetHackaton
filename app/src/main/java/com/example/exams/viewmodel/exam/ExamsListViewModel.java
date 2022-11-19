package com.example.exams.viewmodel.exam;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.example.exams.database.entity.ExamEntity;
import com.example.exams.database.repository.ExamRepository;

import java.util.List;

public class ExamsListViewModel extends AndroidViewModel {
    private Application application;

    private ExamRepository repository;

    private final MediatorLiveData<List<ExamEntity>> observableExams;

    public ExamsListViewModel(@NonNull Application application) {
        super(application);

        this.application = application;

        this.repository = ExamRepository.getInstance();

        observableExams = new MediatorLiveData<>();
        observableExams.setValue(null);

        LiveData<List<ExamEntity>> exams = repository.getAllExams(application);
        observableExams.addSource(exams, observableExams::setValue);
    }

    public LiveData<List<ExamEntity>> getAllExams() {
        return observableExams;
    }
}
