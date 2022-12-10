package com.example.exams.database.entity;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/*@Entity(tableName = "Exam",
    foreignKeys ={
    @ForeignKey(
            entity = RoomEntity.class,
            parentColumns = "id_Room",
            childColumns = "idRoom",
            onDelete = ForeignKey.CASCADE
    ),
    @ForeignKey(
            entity = SubjectEntity.class,
            parentColumns = "id_Subject",
            childColumns = "idSubject",
            onDelete = ForeignKey.CASCADE
    ),
    },indices = {
        @Index(
                value = {"idRoom"}
        ),
        @Index( value = {"idSubject"})
})*/
public class ExamEntity {

    // @Ignore
    public ExamEntity(){

    }

    public ExamEntity(String date, int duration, int numberStudents, int idRoom, int idSubject){
        this.date = date;
        this.duration = duration;
        this.numberStudents = numberStudents;
        this.idRoom = idRoom;
        this.idSubject = idSubject;
    }

    // @PrimaryKey(autoGenerate = true)
    private int idExam;

    // @ColumnInfo(name = "Date")
    private String date;

    // @ColumnInfo(name = "Duration")
    private int duration;

    // @ColumnInfo(name = "Nbre_of_Students")
    private int numberStudents;

    // @ColumnInfo(name = "idSubject")
    private int idSubject;

    // @ColumnInfo(name = "idRoom")
    private int idRoom;

    @Exclude
    public int getIdExam() {
        return idExam;
    }

    public void setIdExam(int id_Exam) {
        this.idExam = id_Exam;
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

    @Exclude
    public int getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(int idSubject) {
        this.idSubject = idSubject;
    }

    @Exclude
    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("date", date);
        result.put("duration", duration);
        result.put("numberStudents", numberStudents);
        // result.put("subjectName", idSubject);
        // result.put("roomName", idRoom);
        return result;
    }
}


