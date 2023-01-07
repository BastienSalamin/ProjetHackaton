package com.example.exams.ui.exam;

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
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.exams.R;
import com.example.exams.database.entity.SubjectEntity;
import com.example.exams.util.OnAsyncEventListener;
import com.example.exams.viewmodel.exam.ExamViewModel;
import com.example.exams.viewmodel.exam.ExamsListViewModel;
import com.example.exams.viewmodel.room.RoomsListViewModel;
import com.example.exams.viewmodel.subject.SubjectsListViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ExamCreationActivity extends AppCompatActivity {
    private static final String TAG = "ExamEditActivity";

    private ConstraintLayout layout;

    private List<SubjectEntity> subjects;

    private List<RoomEntity> rooms;

    private ExamWithStudents examWithStudents;

    private ExamViewModel viewModel;

    private SubjectsListViewModel subjectViewModel;

    private RoomsListViewModel roomViewModel;

    private ExamsListViewModel examViewModel;

    private String[] subjectList;

    private String[] roomList;

    private String[] examInfo;

    /**
     * Method to create the layout in order to be able to create or edit an exam. It retrieves the rooms and subjects already inserted inside the database
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_creation);

        layout = findViewById(R.id.exam_creation_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();

        boolean checkData = intent.hasExtra("ExamInfo");

        if(checkData){
            examInfo = intent.getStringArrayExtra("ExamInfo");

            examViewModel = ViewModelProviders.of(this).get(ExamsListViewModel.class);

            examViewModel.getAllExams().observe(this, examsToList -> {
                if(examsToList != null) {
                    for(ExamEntity exam : examsToList) {
                        if(exam.getIdExam().equals(examInfo[0])){
                            ExamViewModel.Factory factory = new ExamViewModel.Factory(getApplication(), examInfo[0]);
                            viewModel = ViewModelProviders.of((FragmentActivity) this, (ViewModelProvider.Factory) factory).get(ExamViewModel.class);
                            viewModel.getStudentsIdFromExam(exam.getIdExam()).observe(this, entity -> {
                                if(entity != null) {
                                    examWithStudents = entity;
                                }
                            });

                        }
                    }
                }
            });

            subjectViewModel = ViewModelProviders.of(this).get(SubjectsListViewModel.class);

            subjectViewModel.getAllSubjects().observe(this, subjectsToList -> {
                if(subjectsToList != null) {
                    subjects = new ArrayList<>();
                    for(SubjectEntity subject : subjectsToList) {
                        subjects.add(subject);
                    }

                    Collections.sort(subjects, new Comparator<SubjectEntity>() {
                        @Override
                        public int compare(SubjectEntity t1, SubjectEntity t2) {
                            int resultat = t1.getSubjectName().compareTo(t2.getSubjectName());
                            return resultat;
                        }
                    });

                    subjectList = new String[subjects.size()];

                    for(int i = 0 ; i < subjectList.length ; i++) {
                        subjectList[i] = subjects.get(i).getSubjectName();
                    }

                    Spinner subjectSpinner = findViewById(R.id.subjectsSpinner);
                    ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, subjectList);

                    String insertedSubject = examInfo[5];
                    int position = aa.getPosition(insertedSubject);

                    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    subjectSpinner.setAdapter(aa);
                    subjectSpinner.setSelection(position);
                }
            });

            EditText editText1 = findViewById(R.id.examDate);
            editText1.setText(examInfo[1]);

            EditText editText2 = findViewById(R.id.examDuration);
            editText2.setText(examInfo[2]);

            roomViewModel = ViewModelProviders.of(this).get(RoomsListViewModel.class);

            roomViewModel.getAllRooms().observe(this, roomsToList -> {
                if(roomsToList != null) {
                    rooms = new ArrayList<>();
                    for(RoomEntity room : roomsToList) {
                        rooms.add(room);
                    }

                    Collections.sort(rooms, new Comparator<RoomEntity>() {
                        @Override
                        public int compare(RoomEntity t1, RoomEntity t2) {
                            int resultat = t1.getRoomName().compareTo(t2.getRoomName());
                            return resultat;
                        }
                    });

                    roomList = new String[rooms.size()];

                    for(int i = 0 ; i < roomList.length ; i++) {
                        roomList[i] = rooms.get(i).getRoomName();
                    }

                    Spinner roomSpinner = findViewById(R.id.roomsSpinner);
                    ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, roomList);

                    String insertedRoom = examInfo[4];
                    int position = aa.getPosition(insertedRoom);

                    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    roomSpinner.setAdapter(aa);
                    roomSpinner.setSelection(position);
                }
            });

            createButtonModify();
            createButtonDelete();

        } else {
            subjectViewModel = ViewModelProviders.of(this).get(SubjectsListViewModel.class);

            subjectViewModel.getAllSubjects().observe(this, subjectsToList -> {
                if(subjectsToList != null) {
                    subjects = new ArrayList<>();
                    for(SubjectEntity subject : subjectsToList) {
                        subjects.add(subject);
                    }

                    Collections.sort(subjects, new Comparator<SubjectEntity>() {
                        @Override
                        public int compare(SubjectEntity t1, SubjectEntity t2) {
                            int resultat = t1.getSubjectName().compareTo(t2.getSubjectName());
                            return resultat;
                        }
                    });

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

                    Collections.sort(rooms, new Comparator<RoomEntity>() {
                        @Override
                        public int compare(RoomEntity t1, RoomEntity t2) {
                            int resultat = t1.getRoomName().compareTo(t2.getRoomName());
                            return resultat;
                        }
                    });

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
    }

    /**
     * Method to go to the second part of the creation of an exam. It transfers the necessary informations to the next page
     *
     */
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

                EditText editText2 = findViewById(R.id.examDate);
                String examDate = editText2.getText().toString();

                EditText editText3 = findViewById(R.id.examDuration);
                String examDuration = editText3.getText().toString();

                Spinner roomSpinner = findViewById(R.id.roomsSpinner);
                String examRoom = roomSpinner.getSelectedItem().toString();


                if(examDate.equalsIgnoreCase("") || examDuration.equalsIgnoreCase("")) {
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

    /**
     * Creation of the button Modify, which will allow a user to edit the selected exam informations. These informations will be transfered over the next page
     */
    public void createButtonModify() {
        Button button = new Button(this);
        button.setY(850f);
        button.setX(25f);
        button.setLayoutParams(new LinearLayout.LayoutParams(500, 150));
        button.setText(R.string.button_next);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Spinner subjectSpinner = findViewById(R.id.subjectsSpinner);
                String examSubject = subjectSpinner.getSelectedItem().toString();

                EditText editText2 = findViewById(R.id.examDate);
                String examDate = editText2.getText().toString();

                EditText editText3 = findViewById(R.id.examDuration);
                String examDuration = editText3.getText().toString();

                Spinner roomSpinner = findViewById(R.id.roomsSpinner);
                String examRoom = roomSpinner.getSelectedItem().toString();

                if(examDate.equalsIgnoreCase("") || examDuration.equalsIgnoreCase("")) {
                    Context context = getApplicationContext();
                    Toast toast = Toast.makeText(context, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    String[] examData = {examInfo[0], examSubject, examDate, examDuration, examRoom};

                    Intent intent = new Intent(ExamCreationActivity.this, StudentsEditionActivity.class);
                    intent.putExtra("ExamsInfo", examData);
                    startActivity(intent);
                    finish();
                }
            }
        });
        layout.addView(button);
    }

    /**
     * Creation of the button Delete, which removes from the database the selected exam
     */
    public void createButtonDelete() {
        Button button = new Button(this);
        button.setY(850f);
        button.setX(550f);
        button.setLayoutParams(new LinearLayout.LayoutParams(500, 150));
        button.setText(R.string.button_student_delete);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(ExamCreationActivity.this).create();
                alertDialog.setTitle("Supprimer cet examen ?");
                alertDialog.setCancelable(false);

                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Oui", (dialog, which) -> {
                    viewModel.deleteExam(examWithStudents, new OnAsyncEventListener() {
                        @Override
                        public void onSuccess() {
                            Log.d(TAG, "deleteExam: success");
                        }

                        @Override
                        public void onFailure(Exception e) {
                            Log.d(TAG, "deleteExam: failure", e);
                        }
                    });
                    finish();
                });

                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Non", (dialog, which) -> alertDialog.dismiss());
                alertDialog.show();
            }
        });
        layout.addView(button);
    }

}