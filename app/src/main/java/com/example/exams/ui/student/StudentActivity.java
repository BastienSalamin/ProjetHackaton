package com.example.exams.ui.student;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.exams.R;
import com.example.exams.database.entity.StudentEntity;
import com.example.exams.util.OnAsyncEventListener;
import com.example.exams.viewmodel.student.StudentViewModel;

public class StudentActivity extends AppCompatActivity {
    private static final String TAG = "StudentActivity";

    private ConstraintLayout layout;

    private StudentEntity student;

    private StudentViewModel viewModel;

    /**
     * Method to create a new student
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        layout = findViewById(R.id.studentLayout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();

        boolean checkData = intent.hasExtra("StudentInfo");

        if(checkData){
            String[] studentData = intent.getStringArrayExtra("StudentInfo");
            String studentId = studentData[3];

            StudentViewModel.Factory factory = new StudentViewModel.Factory(
                    getApplication(), studentId);
            viewModel = ViewModelProviders.of((FragmentActivity) this, (ViewModelProvider.Factory) factory).get(StudentViewModel.class);
            viewModel.getStudent().observe(this, studentEntity -> {
                if (studentEntity != null) {
                    student = studentEntity;
                }
            });

            EditText editText1 = findViewById(R.id.studentClass);
            editText1.setText(studentData[0]);

            EditText editText2 = findViewById(R.id.studentSurname);
            editText2.setText(studentData[1]);

            EditText editText3 = findViewById(R.id.studentName);
            editText3.setText(studentData[2]);

            createButtonModify();
            createButtonDelete();
        }
        else {
            StudentViewModel.Factory factory = new StudentViewModel.Factory(getApplication(), "0L");
            viewModel = ViewModelProviders.of((FragmentActivity) this, (ViewModelProvider.Factory) factory).get(StudentViewModel.class);

            createButtonAdd();
        }
    }

    /**
     * Everything about the button to add a new student
     */
    public void createButtonAdd() {
        Button button = new Button(this);
        button.setY(750f);
        button.setX(275f);
        button.setLayoutParams(new LinearLayout.LayoutParams(500, 150));
        button.setText(R.string.button_student_save_changes);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText1 = findViewById(R.id.studentClass);
                String newClass = editText1.getText().toString();

                EditText editText2 = findViewById(R.id.studentSurname);
                String newSurname = editText2.getText().toString();

                EditText editText3 = findViewById(R.id.studentName);
                String newName = editText3.getText().toString();

                if(newClass.equalsIgnoreCase("") || newSurname.equalsIgnoreCase("") || newName.equalsIgnoreCase("")) {
                    Context context = getApplicationContext();
                    Toast toast = Toast.makeText(context, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    student = new StudentEntity(newClass, newSurname, newName);
                    viewModel.createStudent(student, new OnAsyncEventListener() {
                        @Override
                        public void onSuccess() {
                            Log.d(TAG, "createStudent: success");
                        }

                        @Override
                        public void onFailure(Exception e) {
                            Log.d(TAG, "createStudent: failure", e);
                        }
                    });

                    Intent intent = new Intent(StudentActivity.this, StudentsActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        layout.addView(button);
    }

    /**
     * Everything about the button to modify a student
     */
    public void createButtonModify() {
        Button button = new Button(this);
        button.setY(750f);
        button.setX(25f);
        button.setLayoutParams(new LinearLayout.LayoutParams(500, 150));
        button.setText(R.string.button_student_save_changes);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText1 = findViewById(R.id.studentClass);
                String newClass = editText1.getText().toString();

                EditText editText2 = findViewById(R.id.studentSurname);
                String newSurname = editText2.getText().toString();

                EditText editText3 = findViewById(R.id.studentName);
                String newName = editText3.getText().toString();

                if(newClass.equalsIgnoreCase("") || newSurname.equalsIgnoreCase("") || newName.equalsIgnoreCase("")) {
                    Context context = getApplicationContext();
                    Toast toast = Toast.makeText(context, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    student.setClassName(newClass);
                    student.setSurname(newSurname);
                    student.setName(newName);

                    viewModel.updateStudent(student, new OnAsyncEventListener() {
                        @Override
                        public void onSuccess() {
                            Log.d(TAG, "updateStudent: success");
                        }

                        @Override
                        public void onFailure(Exception e) {
                            Log.d(TAG, "updateStudent: failure", e);
                        }
                    });

                    Intent intent = new Intent(StudentActivity.this, StudentsActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        layout.addView(button);
    }

    /**
     * Everything about the button to delete a student
     */
    public void createButtonDelete() {
        Button button = new Button(this);
        button.setY(750f);
        button.setX(550f);
        button.setLayoutParams(new LinearLayout.LayoutParams(500, 150));
        button.setText(R.string.button_student_delete);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(StudentActivity.this).create();
                alertDialog.setTitle("Supprimer "+ student.getName() + " " + student.getSurname() + " ?");
                alertDialog.setCancelable(false);

                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Oui", (dialog, which) -> {
                    viewModel.deleteStudent(student, new OnAsyncEventListener() {
                        @Override
                        public void onSuccess() {
                            Log.d(TAG, "deleteStudent: success");
                        }

                        @Override
                        public void onFailure(Exception e) {
                            Log.d(TAG, "deleteStudent: failure", e);
                        }
                    });

                    Intent intent = new Intent(StudentActivity.this, StudentsActivity.class);
                    startActivity(intent);
                    finish();
                });

                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Non", (dialog, which) -> alertDialog.dismiss());
                alertDialog.show();
            }
        });
        layout.addView(button);
    }
}