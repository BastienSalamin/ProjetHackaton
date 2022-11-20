package com.example.exams.database;

import androidx.lifecycle.MutableLiveData;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;
import androidx.annotation.NonNull;
import android.util.Log;

import java.util.concurrent.Executors;

import com.example.exams.database.CrossRef.ExamsStudents;
import com.example.exams.database.dao.ExamDao;
import com.example.exams.database.dao.ExamsStudentsDao;
import com.example.exams.database.dao.RoomDao;
import com.example.exams.database.dao.StudentDao;
import com.example.exams.database.dao.SubjectDao;
import com.example.exams.database.entity.ExamEntity;
import com.example.exams.database.entity.RoomEntity;
import com.example.exams.database.entity.StudentEntity;
import com.example.exams.database.entity.SubjectEntity;

@Database(entities = {StudentEntity.class, ExamEntity.class, RoomEntity.class, SubjectEntity.class, ExamsStudents.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static final String TAG = "AppDatabase";
    private static AppDatabase instance;
    private static final String DATABASE_NAME = "exams-database";
    public abstract StudentDao studentDao();
    public abstract ExamDao examDao();
    public abstract RoomDao roomDao();
    public abstract SubjectDao subjectDao();
    public abstract ExamsStudentsDao examsStudentsDao();
    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

    public static AppDatabase getInstance(final Context context) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null) {
                    instance = buildDatabase(context.getApplicationContext());
                    instance.updateDatabaseCreated(context.getApplicationContext());
                }
            }
        }
        return instance;
    }

    private static AppDatabase buildDatabase(final Context appContext) {
        Log.i(TAG, "La base de données va être initialisée.");
        return Room.databaseBuilder(appContext, AppDatabase.class, DATABASE_NAME).addCallback(new Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
                Executors.newSingleThreadExecutor().execute(() -> {
                    AppDatabase database = AppDatabase.getInstance(appContext);
                    initializeDemoData(database);
                    database.setDatabaseCreated();
                });
            }
        }).build();
    }

    public static void initializeDemoData(final AppDatabase database) {
        Executors.newSingleThreadExecutor().execute(() -> {
            database.runInTransaction(() -> {
                Log.i(TAG, "Suppression des données enregistrées.");
                database.studentDao().deleteAll();
                database.subjectDao().deleteAll();
                database.examDao().deleteAll();
                database.roomDao().deleteAll();
                database.examsStudentsDao().deleteAll();

                DatabaseInitializer.populateDatabase(database);
            });
        });
    }

    private void updateDatabaseCreated(final Context context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            Log.i(TAG, "Base de données initialisée.");
            setDatabaseCreated();
        }
    }

    private void setDatabaseCreated() {
        mIsDatabaseCreated.postValue(true);
    }
}
