package com.example.exams.database.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.exams.database.entity.EmotionEntity;
import com.example.exams.database.entity.PersonEntity;
import com.example.exams.database.firebase.RoomLiveData;
import com.example.exams.util.OnAsyncEventListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class PersonRepository {
    private static PersonRepository instance;

    private PersonRepository(){}

    public static PersonRepository getInstance(){
        if(instance == null){
            synchronized (PersonRepository.class){
                if(instance == null){
                    instance = new PersonRepository();
                }
            }
        }
        return instance;
    }

    public LiveData<List<PersonEntity>> getAllPersons(Application application){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("persons");
        return new PersonListLiveData(reference);
    }

    public LiveData<EmotionEntity> getEmotion(final String personId, Application application) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("persons").child(personId);
        return new PersonLiveData(reference);
    }

    public void insert(final PersonEntity person, final OnAsyncEventListener callback){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("persons");

        reference.push().setValue(person, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError != null) {
                    callback.onFailure(databaseError.toException());
                } else {
                    callback.onSuccess();
                }
            }
        });
    }

    public void update(final PersonEntity person, OnAsyncEventListener callback) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("persons");
        reference.child(person.getIdPerson()).updateChildren(person.toMap(),(databaseError, databaseReference) -> {
            if (databaseError != null) {
                callback.onFailure(databaseError.toException());
            } else {
                callback.onSuccess();
            }
        });
    }

    public void delete(final PersonEntity person, OnAsyncEventListener callback){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("persons");
        reference.child(person.getIdPerson()).removeValue((databaseError, databaseReference) -> {
            if (databaseError != null) {
                callback.onFailure(databaseError.toException());
            } else {
                callback.onSuccess();
            }
        });
    }
}
