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



    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final AppDatabase database;

        PopulateDbAsync(AppDatabase db) {
            database = db;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // populateWithTestData(database);
            return null;
        }
    }
}
