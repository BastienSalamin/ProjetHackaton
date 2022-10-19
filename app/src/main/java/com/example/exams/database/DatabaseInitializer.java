package com.example.exams.database;

import android.os.AsyncTask;
import android.util.Log;

import com.example.exams.database.entity.ExamEntity;
import com.example.exams.database.entity.StudentEntity;

public class DatabaseInitializer {
    public static final String TAG = "DatabaseInitializer";

    public static void populateDatabase(final AppDatabase db) {
        Log.i(TAG, "Insertion des données de démonstration.");
        PopulateDbAsync task = new PopulateDbAsync(db);
        task.execute();
    }

    private static void addStudent(final AppDatabase db, final String className, final String surname, final String name) {
        StudentEntity student = new StudentEntity(className, surname, name);
        db.studentDao().insert(student);
    }

    private static void populateWithTestData(AppDatabase db) {
        db.studentDao().deleteAll();

        addStudent(db, "605_3", "Martroye de Joly", "Alexandre");
        addStudent(db, "605_3", "Salamin", "Bastien");
        addStudent(db, "607_3", "Amano", "Maya");
    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final AppDatabase database;

        PopulateDbAsync(AppDatabase db) {
            database = db;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            populateWithTestData(database);
            return null;
        }
    }
}
