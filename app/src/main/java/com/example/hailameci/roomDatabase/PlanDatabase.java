package com.example.hailameci.roomDatabase;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.Calendar;
import java.util.GregorianCalendar;

@Database(entities = Plan.class, version = 1)
public abstract class PlanDatabase extends RoomDatabase {

    private static PlanDatabase instance;

    public abstract PlanDao planDao();

    public static synchronized PlanDatabase getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    PlanDatabase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static Callback roomCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private PlanDao planDao;

        private PopulateDbAsyncTask(PlanDatabase db) {
            planDao = db.planDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            planDao.insert(new Plan("CFR Cluj", "CS U Craiova", "07/05/2020", "Dr Constantin Radulescu",
                    true, 25, 2,
                    "03/07/2020",
                    "05/07/2020",
                    "Grand hotel italia"));

            planDao.insert(new Plan("Hermannstadt", "CFR Cluj","07/06/2020", "Stadionul National",
                    false, 20, 3,
                    "07/07/2020",
                    "10/07/2020",
                    "Pensiunea hermannstadt"));
            return null;
        }
    }

}
