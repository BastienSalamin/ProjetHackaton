package com.example.exams.database.pojo;

import com.example.exams.database.entity.EmotionEntity;
import com.example.exams.database.entity.SubjectEntity;
import com.google.firebase.database.Exclude;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SubjectWithEmotions {
    public SubjectEntity subject;

    public List<EmotionEntity> emotions;

    private String id;

    public List<String> idSubject = new ArrayList<>();

    @Exclude

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Exclude
    public HashMap<String, Object> toMap(){
        HashMap<String,Object> result = new HashMap<>();
        for(EmotionEntity emotion : emotions){
            result.put(emotion.getIdEmotion(), true);
        }
        return result;
    }
}
