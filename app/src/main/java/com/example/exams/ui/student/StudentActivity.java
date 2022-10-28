package com.example.exams.ui.student;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.example.exams.R;

public class StudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        Intent intent = getIntent();

        boolean checkData = intent.hasExtra("StudentInfo");

        if(checkData == true){
            String[] studentData = intent.getStringArrayExtra("StudentInfo");

            EditText editText1 = findViewById(R.id.studentClass);
            editText1.setText(studentData[0]);

            EditText editText2 = findViewById(R.id.studentSurname);
            editText2.setText(studentData[1]);

            EditText editText3 = findViewById(R.id.studentName);
            editText3.setText(studentData[2]);
        }
    }
}