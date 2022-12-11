package com.example.exams.database.pojo;

import com.example.exams.database.entity.ExamEntity;
import com.example.exams.database.entity.StudentEntity;
import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.List;

public class ExamWithStudents {
    /*@Embedded*/
    @Exclude
    public ExamEntity exam;

    /*@Relation(
            parentColumn = "idExam",
            entityColumn = "idStudent",
            associateBy = @Junction(ExamsStudents.class)
    )*/

    public List<StudentEntity> students;

    @Exclude
    public HashMap<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        for(StudentEntity student : students) {
            result.put(student.getIdStudent(), true);
        }
        return result;
    }
}
