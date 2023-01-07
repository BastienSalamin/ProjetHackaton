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
import android.widget.TextView;

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
        setContentView(R.layout.techers_view);

        List<TextView> tvList = new ArrayList<>();

        TextView tvITS = findViewById(R.id.ITS);
        tvList.add(tvITS);

        TextView tvMaths = findViewById(R.id.Maths);
        tvList.add(tvMaths);

        TextView tvBPM = findViewById(R.id.BPM);
        tvList.add(tvBPM);

        TextView tvPOO = findViewById(R.id.POO);
        tvList.add(tvPOO);


        for(TextView tv : tvList){
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i  = new Intent(MainActivity.this, SchoolClassView.class);
                    i.putExtra("class", tv.getText());

                    startActivity(i);
                }
            });
        }

    }
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        /*
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
                if (i >= 0 && i < exams.size()) {
                    ExamEntity examSelected = exams.get(i);
                    String[] examInfos = {examSelected.getIdExam(), examSelected.getDate(), Integer.toString(examSelected.getDuration()), Integer.toString(examSelected.getNumberStudents()), examSelected.getRoomName(), examSelected.getSubjectName()};
                    Intent intent = new Intent(MainActivity.this, ExamCreationActivity.class);
                    intent.putExtra("ExamInfo", examInfos);
                    startActivity(intent);
                }

            }
        });
        listView.setAdapter(listAdapter);
    }


    public void browseStudents(View view) {
        Intent intent = new Intent(this, StudentsActivity.class);
        startActivity(intent);
    }


    public void createExam(View view) {
        Intent intent = new Intent(this, ExamCreationActivity.class);
        startActivity(intent);
    }
    */
}