package com.example.exams.database.pojo;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.exams.database.entity.ExamEntity;
import com.example.exams.database.entity.SubjectEntity;

import java.util.List;

public class SubjectWithExams {
    @Embedded
    public SubjectEntity subject;

    @Relation(parentColumn = "id_subject", entityColumn = "idSubject", entity = ExamEntity.class)
    public List<ExamEntity> exams;


}
