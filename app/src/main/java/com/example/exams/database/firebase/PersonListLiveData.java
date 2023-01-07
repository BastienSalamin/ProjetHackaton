package com.example.exams.database.firebase;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.exams.database.entity.EmotionEntity;
import com.example.exams.database.entity.PersonEntity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PersonListLiveData extends LiveData<List<PersonEntity>> {
    private static final String TAG = "PersonListLiveData";

    private final DatabaseReference reference;
    private final PersonListLiveData.MyValueEventListener listener = new PersonListLiveData.MyValueEventListener();

    public PersonListLiveData(DatabaseReference ref) {
        reference = ref;
    }

    @Override
    protected void onActive() {
        Log.d(TAG, "onActive");
        reference.addValueEventListener(listener);
    }

    @Override
    protected void onInactive() {
        Log.d(TAG, "onInactive");
    }

    private class MyValueEventListener implements ValueEventListener {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            setValue(toEmotionList(dataSnapshot));
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            Log.e(TAG, "Can't listen to query " + reference, databaseError.toException());
        }
    }

    private List<PersonEntity> toEmotionList(DataSnapshot snapshot) {
        List<PersonEntity> personEntityList = new ArrayList<>();
        for(DataSnapshot childSnapshot : snapshot.getChildren()) {
            PersonEntity person = childSnapshot.getValue(PersonEntity.class);
            person.setIdPerson(childSnapshot.getKey());
            personEntityList.add(person);
        }
        return personEntityList;
    }
}
