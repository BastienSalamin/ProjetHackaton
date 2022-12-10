package com.example.exams.viewmodel.exam;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.exams.BaseApp;
import com.example.exams.database.CrossRef.ExamsStudents;
import com.example.exams.database.entity.ExamEntity;
import com.example.exams.database.pojo.ExamWithStudents;
import com.example.exams.database.repository.ExamRepository;
import com.example.exams.util.OnAsyncEventListener;

import java.util.List;

public class ExamViewModel extends AndroidViewModel {
    private Application application;

    private ExamRepository repository;

    private final MediatorLiveData<ExamEntity> observableExam;

    public ExamViewModel(@NonNull Application application, final String examId, ExamRepository repository) {
        super(application);

        this.application = application;

        this.repository = repository;

        observableExam = new MediatorLiveData<>();

        observableExam.setValue(null);

        LiveData<ExamEntity> exam = repository.getExam(examId, application);

        observableExam.addSource(exam, observableExam::setValue);
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {
        @NonNull
        private final Application application;

        private final String examId;

        private final ExamRepository repository;

        public Factory(@NonNull Application application, String examId) {
            this.application = application;
            this.examId = examId;
            repository = ((BaseApp) application).getExamRepository();
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            return (T) new ExamViewModel(application, examId, repository);
        }
    }

    public LiveData<ExamEntity> getExam() {
        return observableExam;
    }

    public LiveData<List<ExamWithStudents>> getStudentsIdFromExam(String examId) {
        return repository.getStudentsIdFromExam(examId, application);
    }

    public void createExam(ExamWithStudents exam, OnAsyncEventListener callback) {
        repository.insert(exam, callback, application);
    }

    public void updateExam(ExamWithStudents exam, OnAsyncEventListener callback) {
        repository.update(exam, callback, application);
    }

    public void deleteExam(ExamWithStudents exam, OnAsyncEventListener callback) {
        repository.delete(exam, callback, application);
    }
}
