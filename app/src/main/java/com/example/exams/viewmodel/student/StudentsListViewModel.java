package com.example.exams.viewmodel.student;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.exams.BaseApp;
import com.example.exams.database.entity.StudentEntity;
import com.example.exams.database.repository.StudentRepository;
import com.example.exams.util.OnAsyncEventListener;

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

    public static class Factory extends ViewModelProvider.NewInstanceFactory {
        @NonNull
        private final Application application;

        private final StudentRepository studentRepository;

        public Factory(@NonNull Application application) {
            this.application = application;
            this.studentRepository = ((BaseApp) application).getStudentRepository();
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            return (T) new StudentsListViewModel(application);
        }
    }

    public LiveData<List<StudentEntity>> getAllStudents() {
        return observableStudents;
    }

    public void deleteStudents(StudentEntity student, OnAsyncEventListener callback) {
        repository.delete(student, callback, application);
    }
}
