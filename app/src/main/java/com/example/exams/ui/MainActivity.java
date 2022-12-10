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
import android.widget.ListView;

import com.example.exams.R;
import com.example.exams.database.entity.ExamEntity;
import com.example.exams.ui.exam.ExamCreationActivity;
import com.example.exams.ui.mgmt.SettingsActivity;
import com.example.exams.ui.student.StudentsActivity;
import com.example.exams.viewmodel.exam.ExamsListViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<ExamEntity> exams;

    private ExamsListViewModel viewModel;

    private List<String> examData = new ArrayList<>();

    /**
     * Display the exams retrieved from the database
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewModel = ViewModelProviders.of(this).get(ExamsListViewModel.class);

        viewModel.getAllExams().observe(this, examsToList -> {
            if(examsToList != null) {
                exams = new ArrayList<>();
                for(ExamEntity exam : examsToList) {
                    exams.add(exam);
                }

                ListView list = findViewById(R.id.examList);

                if(list != null){
                    for (int i = 0; i < exams.size() ; i++) {
                        createExamsList(list, i);
                    }
                }
            }
        });
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
     * Display an exam retrieved from the database, depending on his position. It can be selected
     * @param listView
     * @param pos
     */
    private void createExamsList(ListView listView, int pos) {
        ExamEntity exam = exams.get(pos);

        String examString = "Examen " + (pos+1) + " :\nDate : " + exam.getDate() + "   Durée : " + exam.getDuration() + "     Étudiants : " + exam.getNumberStudents();

        examData.add(examString);

        ArrayList<String> examsList = new ArrayList<String>();
        examsList.addAll(examData);

        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, examsList);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ExamEntity examSelected = exams.get(i);
                String[] examInfos = {examSelected.getIdExam(), examSelected.getDate(), Integer.toString(examSelected.getDuration()), Integer.toString(examSelected.getNumberStudents()), examSelected.getRoom(), examSelected.getSubject()};

                Intent intent = new Intent(MainActivity.this, ExamCreationActivity.class);
                intent.putExtra("ExamInfo", examInfos);
                startActivity(intent);
            }
        });
        listView.setAdapter(listAdapter);
    }

    /**
     * Method to move in the StudentsActivity window, which display the list of all the students inside the database
     * @param view
     */
    public void browseStudents(View view) {
        Intent intent = new Intent(this, StudentsActivity.class);
        startActivity(intent);
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