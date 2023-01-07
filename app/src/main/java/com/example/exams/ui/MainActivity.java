package com.example.exams.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.exams.R;
import com.example.exams.database.entity.ExamEntity;
import com.example.exams.ui.exam.ExamCreationActivity;
import com.example.exams.ui.mgmt.SettingsActivity;
import com.example.exams.ui.student.StudentsActivity;
import com.example.exams.viewmodel.exam.ExamsListViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<ExamEntity> exams;

    private ExamsListViewModel viewModel;

    private List<String> examData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onResume() {
        super.onResume();
        examData.clear();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Method to move in the StudentsActivity window, which display the list of all the students inside the database
     * @param view
     */
    public void browseStudents(View view) {
        EditText editText = findViewById(R.id.login);
        String login = editText.getText().toString();

        if(login.contains("prof")) {
            Intent intent = new Intent(this, ExamCreationActivity.class);
            intent.putExtra("ProfName", login);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, StudentsActivity.class);
            startActivity(intent);
        }
    }

    /**
     * Method to move in the ExamCreationActivity window, which allow the user to create a new exam
     * @param view
     */
    public void createExam(View view) {
        Intent intent = new Intent(this, ExamCreationActivity.class);
        startActivity(intent);
    }
}