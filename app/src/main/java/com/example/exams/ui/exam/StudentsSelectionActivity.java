package com.example.exams.ui.exam;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.DKGRAY;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.exams.R;
import com.example.exams.database.entity.ExamEntity;
import com.example.exams.database.entity.StudentEntity;
import com.example.exams.viewmodel.exam.ExamViewModel;
import com.example.exams.viewmodel.student.StudentsListViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StudentsSelectionActivity extends AppCompatActivity {
    private String[] examData;

    private ArrayList<CheckBox> checkList = new ArrayList<CheckBox>();

    private ExamEntity exam;

    private List<StudentEntity> students;

    private ExamViewModel examViewModel;

    private StudentsListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_selection);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        examData = intent.getStringArrayExtra("ExamsInfo");

        viewModel = ViewModelProviders.of(this).get(StudentsListViewModel.class);

        viewModel.getAllStudents().observe(this, studentsToList -> {
            if(studentsToList != null) {
                students = new ArrayList<>();
                for (StudentEntity student : studentsToList) {
                    students.add(student);
                }

                Collections.sort(students, new Comparator<StudentEntity>() {
                    @Override
                    public int compare(StudentEntity student1, StudentEntity student2) {
                        int resultat = student1.getClassName().compareTo(student2.getClassName());
                        if(resultat != 0) {
                            return resultat;
                        }
                        return student1.getSurname().compareTo(student2.getSurname());
                    }
                });

                LinearLayout table = findViewById(R.id.tableSelection);

                int size = students.size();

                createTitle(table);

                for (int i = 0 ; i < size ; i++) {
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

        String[] titles={"Classe", "Nom", "Prénom", ""};

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

        String[] studentData = {student.getClassName(), student.getSurname(), student.getName(), String.valueOf(student.getIdStudent())};

        LinearLayout row = new LinearLayout(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 6);
        row.setLayoutParams(layoutParams);
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setWeightSum(100);
        for (int i = 0 ; i < 3 ; i++) {
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

        CheckBox check = new CheckBox(this);
        check.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, (float) 25));
        checkList.add(check);
        row.addView(check);

        layout.addView(row);
    }

    public void createExam(View view) {
        List<StudentEntity> checkedStudents = new ArrayList<>();
        int count = 0;
        for(int i = 0 ; i < checkList.size() ; i++) {
            if(checkList.get(i).isChecked() == true) {
                StudentEntity student = students.get(i);
                checkedStudents.add(student);
                count++;
            }
        }

        if(count == 0){
            Context context = getApplicationContext();
            Toast toast = Toast.makeText(context, "Veuillez choisir au moins un étudiant", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        String data = "";
        for (int i = 0 ; i < examData.length ; i++) {
            data += examData[i] + " ";
        }
        System.out.println(data);

        System.out.println("Nombre d'étudiants : " + count);
        for(int i = 0 ; i < checkedStudents.size() ; i++) {
            StudentEntity student = checkedStudents.get(i);
            System.out.println(student.getIdStudent() + " " + student.getClassName() + " " + student.getSurname() + " " + student.getName());
        }
    }

}