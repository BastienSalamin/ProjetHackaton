package com.example.exams.viewmodel.room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import java.util.List;

public class RoomsListViewModel extends AndroidViewModel {
    private Application application;

    private RoomRepository repository;

    private final MediatorLiveData<List<RoomEntity>> observableRooms;

    public RoomsListViewModel(@NonNull Application application) {
        super(application);

        this.application = application;

        this.repository = RoomRepository.getInstance();

        observableRooms = new MediatorLiveData<>();
        observableRooms.setValue(null);

        LiveData<List<RoomEntity>> rooms = repository.getAllRooms(application);
        observableRooms.addSource(rooms, observableRooms::setValue);
    }

    public LiveData<List<RoomEntity>> getAllRooms() {
        return observableRooms;
    }
}
