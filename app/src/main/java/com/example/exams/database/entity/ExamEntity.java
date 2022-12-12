package com.example.exams.database.entity;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class ExamEntity {

    public ExamEntity(){

    }

    public ExamEntity(String date, int duration, int numberStudents, String subjectName, String roomName){
        this.date = date;
        this.duration = duration;
        this.numberStudents = numberStudents;
        this.subjectName = subjectName;
        this.roomName = roomName;
    }

    private String idExam;

    private String date;

    private int duration;

    private int numberStudents;

    private String subjectName;

    private String roomName;

    @Exclude
    public String getIdExam() {
        return idExam;
    }

    public void setIdExam(String idExam) {
        this.idExam = idExam;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getNumberStudents() {
        return numberStudents;
    }

    public void setNumberStudents(int numberStudents) {
        this.numberStudents = numberStudents;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("date", date);
        result.put("duration", duration);
        result.put("numberStudents", numberStudents);
        result.put("subjectName", subjectName);
        result.put("roomName", roomName);
        return result;
    }
}


