package com.example.hmod_.popularmoviesstageone.DataBase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;

@Database(entities = {FavoritesMovieEntity.class}, version = 1, exportSchema = false)
public abstract class FavoritesMoviesDatabase extends RoomDatabase {
    private static final String TAG = FavoritesMoviesDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "favoritesMoviesList";
    private static FavoritesMoviesDatabase sInstance;


    public static FavoritesMoviesDatabase getsInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                Log.d(TAG, "Creating New Database Instance");
                // Will Create the database if not-exists and get a referecne to it
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        FavoritesMoviesDatabase.class,
                        FavoritesMoviesDatabase.DATABASE_NAME)
                        // Has to Be MOVED TO BACKGROUND THREAD
                        .allowMainThreadQueries()
                        .build();
            }
        }
        Log.d(TAG, "Getting the database instance");
        return sInstance;
    }


    public abstract FavoritesMovieDao favoritesMovieDao();
}
