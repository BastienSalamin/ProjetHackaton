package com.example.exams.database;

import android.os.AsyncTask;
import android.util.Log;

import com.example.exams.database.entity.ExamEntity;
import com.example.exams.database.entity.RoomEntity;
import com.example.exams.database.entity.StudentEntity;
import com.example.exams.database.entity.SubjectEntity;

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

    private static void addSubject(final AppDatabase db, final String subjectName) {
        SubjectEntity subject = new SubjectEntity(subjectName);
        db.subjectDao().insert(subject);
    }

    private static void addRoom(final AppDatabase db, final String roomName) {
        RoomEntity room = new RoomEntity(roomName);
        db.roomDao().insert(room);
    }

    private static void populateWithTestData(AppDatabase db) {
        db.studentDao().deleteAll();

        addStudent(db, "605_3", "Martroye de Joly", "Alexandre");
        addStudent(db, "605_3", "Salamin", "Bastien");
        addStudent(db, "607_3", "Amano", "Maya");
        addStudent(db, "605_3", "Vialard", "Céline");
        addStudent(db, "605_3", "Brouchoud", "François");
        addStudent(db, "605_3", "Clerc", "Théo");
        addStudent(db, "605_3", "Dellea", "Jérémie");
        addStudent(db, "605_3", "Bétrisey", "Julienne");
        addStudent(db, "605_3", "Bourquin", "Jonathan");
        addStudent(db, "605_3", "Marguet", "Romain");
        addStudent(db, "605_3", "Del Buono", "Luca");
        addStudent(db, "605_3", "Gomez Cabeiro", "Sergio");
        addStudent(db, "605_3", "Michelet", "Arnaud");
        addStudent(db, "605_3", "Renna", "Zacharie");
        addStudent(db, "605_3", "De Blasi", "Simon");
        addStudent(db, "605_3", "Lonfat", "Milena");
        addStudent(db, "605_3", "Rey", "Guillaume");
        addStudent(db, "605_3", "Borrajo", "Elias");

        db.subjectDao().deleteAll();

        addSubject(db, "Statistiques");
        addSubject(db, "Mathématiques");
        addSubject(db, "Programmation");
        addSubject(db, "Cloud");
        addSubject(db, "ASP.NET");

        db.roomDao().deleteAll();

        addRoom(db, "VS-BEL.N100");
        addRoom(db, "VS-BEL.N101");
        addRoom(db, "VS-BEL.N102");
        addRoom(db, "VS-BEL.N103");
        addRoom(db, "VS-BEL.N200");
        addRoom(db, "VS-BEL.N201");
        addRoom(db, "VS-BEL.N202");
        addRoom(db, "VS-BEL.N203");
        addRoom(db, "VS-BEL.N300");
        addRoom(db, "VS-BEL.N301");
        addRoom(db, "VS-BEL.N302");
        addRoom(db, "VS-BEL.N303");
        addRoom(db, "VS-BEL.N400");
        addRoom(db, "VS-BEL.N401");
        addRoom(db, "VS-BEL.N402");
        addRoom(db, "VS-BEL.N403");
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
