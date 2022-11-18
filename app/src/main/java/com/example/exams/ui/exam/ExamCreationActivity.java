package com.example.exams.ui.exam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.exams.R;
import com.example.exams.database.entity.RoomEntity;
import com.example.exams.database.entity.SubjectEntity;
import com.example.exams.viewmodel.room.RoomsListViewModel;
import com.example.exams.viewmodel.subject.SubjectsListViewModel;

import java.util.ArrayList;
import java.util.List;

public class ExamCreationActivity extends AppCompatActivity {
    private ConstraintLayout layout;

    private List<SubjectEntity> subjects;

    private List<RoomEntity> rooms;

    private SubjectsListViewModel subjectViewModel;

    private RoomsListViewModel roomViewModel;

    private String[] subjectList;

    private String[] roomList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_creation);

        layout = findViewById(R.id.exam_creation_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        subjectViewModel = ViewModelProviders.of(this).get(SubjectsListViewModel.class);

        subjectViewModel.getAllSubjects().observe(this, subjectsToList -> {
            if(subjectsToList != null) {
                subjects = new ArrayList<>();
                for(SubjectEntity subject : subjectsToList) {
                    subjects.add(subject);
                }

                subjectList = new String[subjects.size()];

                for(int i = 0 ; i < subjectList.length ; i++) {
                    subjectList[i] = subjects.get(i).getSubjectName();
                }

                Spinner subjectSpinner = findViewById(R.id.subjectsSpinner);
                ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, subjectList);
                aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                subjectSpinner.setAdapter(aa);
            }
        });

        roomViewModel = ViewModelProviders.of(this).get(RoomsListViewModel.class);

        roomViewModel.getAllRooms().observe(this, roomsToList -> {
            if(roomsToList != null) {
                rooms = new ArrayList<>();
                for(RoomEntity room : roomsToList) {
                    rooms.add(room);
                }

                roomList = new String[rooms.size()];

                for(int i = 0 ; i < roomList.length ; i++) {
                    roomList[i] = rooms.get(i).getRoomName();
                }

                Spinner roomSpinner = findViewById(R.id.roomsSpinner);
                ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, roomList);
                aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                roomSpinner.setAdapter(aa);
            }
        });

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
                Spinner subjectSpinner = findViewById(R.id.subjectsSpinner);
                String examSubject = subjectSpinner.getSelectedItem().toString();
                int position1 = subjectSpinner.getSelectedItemPosition();
                SubjectEntity subject = subjects.get(position1);
                int subjectId = subject.getId_Subject();
                String subjectIdString = Integer.toString(subjectId);

                EditText editText2 = findViewById(R.id.examDate);
                String examDate = editText2.getText().toString();

                EditText editText3 = findViewById(R.id.examDuration);
                String examDuration = editText3.getText().toString();

                Spinner roomSpinner = findViewById(R.id.roomsSpinner);
                String examRoom = roomSpinner.getSelectedItem().toString();
                int position2 = roomSpinner.getSelectedItemPosition();
                RoomEntity room = rooms.get(position2);
                int roomId = room.getId_Room();
                String roomIdString = Integer.toString(roomId);

                if(examDate.equalsIgnoreCase("") || examDuration.equalsIgnoreCase("")) {
                    Context context = getApplicationContext();
                    Toast toast = Toast.makeText(context, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    String[] examData = {subjectIdString, examSubject, examDate, examDuration, roomIdString, examRoom};

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