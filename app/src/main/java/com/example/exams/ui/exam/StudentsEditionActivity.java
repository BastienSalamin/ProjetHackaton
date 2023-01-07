package com.example.exams.ui.exam;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.DKGRAY;
import static android.graphics.Color.LTGRAY;
import static android.graphics.Color.WHITE;

import static com.example.exams.ui.mgmt.SettingsActivity.isDarkModeOn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.exams.R;
import com.example.exams.util.OnAsyncEventListener;
import com.example.exams.viewmodel.exam.ExamViewModel;
import com.example.exams.viewmodel.student.StudentsListViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StudentsEditionActivity extends AppCompatActivity {
    private static final String TAG = "ExamEditionActivity";

    private ArrayList<CheckBox> checkList = new ArrayList<CheckBox>();

    private ExamEntity exam;

    private ExamWithStudents examUpdated;

    private List<StudentEntity> students;

    private ExamWithStudents examStudents;

    private ExamViewModel examViewModel;

    private StudentsListViewModel studentsViewModel;

    /**
     * Display all the students, with a checked box for the students the user added for his exam
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_edition);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        String[] examData = intent.getStringArrayExtra("ExamsInfo");

        ExamViewModel.Factory factory = new ExamViewModel.Factory(getApplication(), examData[0]);
        examViewModel = ViewModelProviders.of((FragmentActivity) this, (ViewModelProvider.Factory) factory).get(ExamViewModel.class);
        examViewModel.getExam().observe(this, examEntity -> {
            if(examEntity != null) {
                exam = examEntity;
                exam.setSubjectName(examData[1]);
                exam.setDate(examData[2]);
                exam.setDuration(Integer.parseInt(examData[3]));
                exam.setRoomName(examData[4]);
            }
        });

        examViewModel.getStudentsIdFromExam(examData[0]).observe(this, entity -> {
            if(entity != null) {
                    examStudents = entity;
            }
        });

        studentsViewModel = ViewModelProviders.of(this).get(StudentsListViewModel.class);
        studentsViewModel.getAllStudents().observe(this, studentsToList -> {
            if(studentsToList != null) {
                students = new ArrayList<>();
                for(StudentEntity student : studentsToList) {
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

                LinearLayout table = findViewById(R.id.tableEdition);

                int size = students.size();

                createTitle(table);

                for (int i = 0 ; i < size ; i++) {
                    createTable(table, i);
                }

                for(int i = 0 ; i < checkList.size() ; i++) {
                    for(int j = 0 ; j < examStudents.idStudent.size() ; j++) {
                            if(examStudents.idStudent.get(j).equals(students.get(i).getIdStudent())){
                                checkList.get(i).setChecked(true);
                            }
                    }
                }
            }
        });
    }

    /**
     * Create the title layout for the user interface
     * @param layout
     */
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
            if(isDarkModeOn){
                col1.setTextColor(WHITE);
            } else {
                col1.setTextColor(BLACK);
            }
            col1.setPadding(2,0,0,0);
            col1.setLayoutParams(layoutParams);
            row.addView(col1);
        }
        layout.addView(row);
    }

    /**
     * Create the display of a student and add it inside the layout
     * @param layout
     * @param pos
     */
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

        CheckBox check = new CheckBox(this);
        check.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, (float) 25));
        checkList.add(check);
        row.addView(check);

        layout.addView(row);
    }

    /**
     * Method to edit the exam with the information retrieved from the previous page and the currently checked students
     * @param view
     */
    public void editExam(View view) {
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

        exam.setNumberStudents(count);

        examUpdated = new ExamWithStudents();
        examUpdated.exam = exam;
        examUpdated.students = new ArrayList<StudentEntity>();
        examUpdated.setId(examUpdated.exam.getIdExam());

        for (int i = 0 ; i < checkedStudents.size() ; i++) {
            StudentEntity student = checkedStudents.get(i);
            examUpdated.students.add(student);
            examUpdated.idStudent.add(student.getIdStudent());
        }

        examViewModel.updateExam(examUpdated, new OnAsyncEventListener() {
            @Override
            public void onSuccess() {
                Log.d(TAG, "updateExam: success");
            }

            @Override
            public void onFailure(Exception e) {
                Log.d(TAG, "updateExam: failure", e);
            }
        });
        finish();
    }

}