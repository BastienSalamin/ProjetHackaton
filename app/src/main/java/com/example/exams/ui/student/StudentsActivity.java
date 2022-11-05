package com.example.exams.ui.student;

import static android.graphics.Color.*;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import com.example.exams.R;
import com.example.exams.database.entity.StudentEntity;
import com.example.exams.ui.MainActivity;
import com.example.exams.viewmodel.student.StudentsListViewModel;

import java.util.ArrayList;
import java.util.List;

public class StudentsActivity extends MainActivity {
    private List<StudentEntity> students;

    private StudentsListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        viewModel = ViewModelProviders.of(this).get(StudentsListViewModel.class);

        viewModel.getAllStudents().observe(this, studentsToList -> {
            if (studentsToList != null) {
                students = new ArrayList<>();
                for (StudentEntity student : studentsToList) {
                    students.add(student);
                }
                LinearLayout table = findViewById(R.id.table);

                int size = students.size();

                createTitle(table);

                for (int i = 0; i < size; i++) {
                    createTable(table, i);
                }
            }
        });
    }

    private void createTitle(LinearLayout layout) {
        LinearLayout row = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 6);
        row.setLayoutParams(params);
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setWeightSum(100);

        String[] titles={"Classe", "Nom", "Prénom", "Statut"};

        for (int i = 0 ; i < 4 ; i++) {
            LinearLayout.LayoutParams layoutParams;
            layoutParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, (float) 25);
            TextView col1 = new TextView(this);
            col1.setText(titles[i]);
            col1.setTextSize(22);
            col1.setTextColor(BLACK);
            col1.setPadding(2,0,0,0);
            col1.setLayoutParams(layoutParams);
            row.addView(col1);
        }
        layout.addView(row);
    }

    private void createTable(LinearLayout layout, int pos) {
        StudentEntity student = students.get(pos);

        String[] studentData = {student.getClassName(), student.getSurname(), student.getName(), " ", String.valueOf(student.getIdStudent())};

        LinearLayout row = new LinearLayout(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 6);
        row.setLayoutParams(layoutParams);
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setWeightSum(100);
        row.setClickable(true);
        row.setFocusable(true);
        row.setBackgroundResource(android.R.drawable.menuitem_background);
        for (int i = 0 ; i < 4 ; i++) {
            LinearLayout.LayoutParams textParams;
            textParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, (float) 25);
            TextView col1 = new TextView(this);
            col1.setText(studentData[i]);
            col1.setTextSize(15);
            col1.setTextColor(DKGRAY);
            col1.setLines(2);
            col1.setPadding(2,0,0,0);
            col1.setLayoutParams(textParams);
            col1.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            col1.setGravity(Gravity.CENTER);
            row.addView(col1);
        }
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentsActivity.this, StudentActivity.class);
                intent.putExtra("StudentInfo", studentData);
                startActivity(intent);



            }
        });
        layout.addView(row);
    }

    public void browseStudent(View view) {
        Intent intent = new Intent(this, StudentActivity.class);
        startActivity(intent);
    }
}