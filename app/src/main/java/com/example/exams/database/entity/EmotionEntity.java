package com.example.exams.database.entity;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class EmotionEntity {

    public EmotionEntity(){

    }

    public EmotionEntity(String emotionName, String emotionEmoji, int percentage, int emotionValue){
        this.emotionName = emotionName;
        this.emotionEmoji = emotionEmoji;
        this.percentage = percentage;
        this.emotionValue = emotionValue;
    }

    private String idEmotion;
    private int emotionValue;
    private String emotionName;
    private String emotionEmoji;
    private int percentage;

    @Exclude

    public String getIdEmotion() {
        return idEmotion;
    }

    public void setIdEmotion(String idEmotion) {
        this.idEmotion = idEmotion;
    }

    public int getEmotionValue() {
        return emotionValue;
    }

    public void setEmotionValue(int emotionValue) {
        this.emotionValue = emotionValue;
    }

    public String getEmotionName() {
        return emotionName;
    }

    public void setEmotionName(String emotionName) {
        this.emotionName = emotionName;
    }

    public String getEmotionEmoji() {
        return emotionEmoji;
    }

    public void setEmotionEmoji(String emotionEmoji) {
        this.emotionEmoji = emotionEmoji;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("emotionValue", emotionValue);
        result.put("emotionName", emotionName);
        result.put("emotionEmoji", emotionEmoji);
        result.put("percentage", percentage);
        return result;
    }
}
