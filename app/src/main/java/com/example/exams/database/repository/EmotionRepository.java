package com.example.exams.database.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.exams.database.entity.EmotionEntity;
import com.example.exams.util.OnAsyncEventListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class EmotionRepository {
    private static EmotionRepository instance;

    private EmotionRepository(){}

    public static EmotionRepository getInstance(){
        if(instance == null){
            synchronized (EmotionRepository.class){
                if(instance == null){
                    instance = new EmotionRepository();
                }
            }
        }
        return instance;
    }

    public LiveData<List<EmotionEntity>> getAllEmotions(Application application){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("emotions");
        return new EmotionListLiveData(reference);
    }

    public LiveData<EmotionEntity> getEmotion(final String emotionId, Application application) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("emotions").child(emotionId);
        return new EmotionLiveData(reference);
    }

    public void insert(final EmotionEntity emotion, final OnAsyncEventListener callback){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("emotions");

        reference.push().setValue(emotion, new DatabaseReference.CompletionListener(){

            @Override
            public void onComplete(DatabaseError error, DatabaseReference ref) {
                if(error != null){
                    callback.onFailure(error.toException());
                }else{
                    callback.onSuccess();
                }
            }
        });

    }

    public void update(final EmotionEntity emotion, OnAsyncEventListener callback){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference= database.getReference("emotions");

        reference.child(emotion.getIdEmotion()).updateChildren(emotion.toMap(),(error, ref) -> {
            if(error != null){
                callback.onFailure(error.toException());
            }else{
                callback.onSuccess();
            }
        });
    }

    public void delete(final EmotionEntity emotion, OnAsyncEventListener callback){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("emotions");
        reference.child(emotion.getIdEmotion()).removeValue((error, ref) -> {
           if(error != null){
               callback.onFailure(error.toException());
           }else {
               callback.onSuccess();
           }
        });
    }
}
