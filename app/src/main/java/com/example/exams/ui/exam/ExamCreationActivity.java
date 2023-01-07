package com.example.exams.ui.exam;

import static android.graphics.Color.DKGRAY;
import static android.graphics.Color.LTGRAY;
import static com.example.exams.ui.mgmt.SettingsActivity.isDarkModeOn;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.exams.R;
import com.example.exams.database.entity.ExamEntity;
import com.example.exams.database.entity.RoomEntity;
import com.example.exams.database.entity.StudentEntity;
import com.example.exams.database.entity.SubjectEntity;
import com.example.exams.database.pojo.ExamWithStudents;
import com.example.exams.ui.student.StudentActivity;
import com.example.exams.ui.student.StudentsActivity;
import com.example.exams.util.OnAsyncEventListener;
import com.example.exams.viewmodel.exam.ExamViewModel;
import com.example.exams.viewmodel.exam.ExamsListViewModel;
import com.example.exams.viewmodel.room.RoomsListViewModel;
import com.example.exams.viewmodel.student.StudentsListViewModel;
import com.example.exams.viewmodel.subject.SubjectsListViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ExamCreationActivity extends AppCompatActivity {
    private List<StudentEntity> students;

    private StudentsListViewModel viewModel;

    private LinearLayout table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_creation);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        String profName = intent.getStringExtra("ProfName");

        TextView textView = findViewById(R.id.profName);
        String salut = textView.getText().toString();

        salut += " " + profName;

        textView.setText(salut);

        viewModel = ViewModelProviders.of(this).get(StudentsListViewModel.class);

        viewModel.getAllStudents().observe(this, studentsToList -> {
            if (studentsToList != null) {
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

                table = findViewById(R.id.tableProf);

                int size = students.size();

                for (int i = 0; i < size; i++) {
                    createTable(table, i);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(table != null) {
            table.removeAllViews();
        }
    }

    private void createTable(LinearLayout layout, int pos) {
        StudentEntity student = students.get(pos);

        String[] studentData = {student.getClassName(), student.getSurname(), student.getName(), String.valueOf(student.getIdStudent())};

        LinearLayout row = new LinearLayout(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 6);
        row.setLayoutParams(layoutParams);
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setWeightSum(100);
        row.setClickable(true);
        row.setFocusable(true);
        row.setBackgroundResource(android.R.drawable.menuitem_background);
        for (int i = 1 ; i < 3 ; i++) {
            LinearLayout.LayoutParams textParams;
            textParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, (float) 50);
            TextView col1 = new TextView(this);
            col1.setText(studentData[i]);
            col1.setTextSize(20);
            if(isDarkModeOn){
                col1.setTextColor(LTGRAY);
            } else {
                col1.setTextColor(DKGRAY);
            }
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
                Intent intent = new Intent(ExamCreationActivity.this, StudentActivity.class);
                intent.putExtra("StudentInfo", studentData);
                startActivity(intent);
            }
        });
        layout.addView(row);
    }

}