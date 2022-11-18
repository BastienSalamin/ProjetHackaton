package com.example.exams.ui.exam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.exams.R;
import com.example.exams.database.entity.RoomEntity;
import com.example.exams.database.entity.SubjectEntity;

import java.util.List;

public class ExamCreationActivity extends AppCompatActivity {
    private ConstraintLayout layout;

    private List<SubjectEntity> subjects;

    private List<RoomEntity> rooms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_creation);

        layout = findViewById(R.id.exam_creation_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        createNextButton();

    }

    public void createNextButton() {
        Button button = new Button(this);
        button.setY(850f);
        button.setX(275f);
        button.setLayoutParams(new LinearLayout.LayoutParams(500, 150));
        button.setText(R.string.button_next);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText1 = findViewById(R.id.examSubject);
                String examSubject = editText1.getText().toString();

                EditText editText2 = findViewById(R.id.examDate);
                String examDate = editText2.getText().toString();

                EditText editText3 = findViewById(R.id.examDuration);
                String examDuration = editText3.getText().toString();

                EditText editText4 = findViewById(R.id.examRoom);
                String examRoom = editText4.getText().toString();

                if(examSubject.equalsIgnoreCase("") || examDate.equalsIgnoreCase("") || examDuration.equalsIgnoreCase("") || examRoom.equalsIgnoreCase("")) {
                    Context context = getApplicationContext();
                    Toast toast = Toast.makeText(context, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    String[] examData = {examSubject, examDate, examDuration, examRoom};

                    Intent intent = new Intent(ExamCreationActivity.this, StudentsSelectionActivity.class);
                    intent.putExtra("ExamsInfo", examData);
                    startActivity(intent);
                    finish();
                }
            }
        });
        layout.addView(button);
    }

}