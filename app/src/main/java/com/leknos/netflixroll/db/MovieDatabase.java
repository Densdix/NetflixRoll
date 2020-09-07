package com.leknos.netflixroll.db;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.leknos.netflixroll.model.Movie;

@Database(entities = {Movie.class}, version = 1)
public abstract class MovieDatabase extends RoomDatabase {
    private static MovieDatabase instance;

    public abstract MovieDao movieDao();

    public static synchronized MovieDatabase getInstance(Context context) {
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    MovieDatabase.class, "movie_db")
                    .fallbackToDestructiveMigration()
                    .build();
            Log.d("EXECUTEEXECUTE1", "doInBackground: EXECUTE");
        }
        return instance;
    }

}
