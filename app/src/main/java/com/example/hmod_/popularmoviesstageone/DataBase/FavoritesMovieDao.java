package com.example.hmod_.popularmoviesstageone.DataBase;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;


@Dao
public interface FavoritesMovieDao {
    @Query("SELECT * FROM favoritesMovies")
    LiveData<List<FavoritesMovieEntity>> loadAllMovies();

    @Insert
    void insertMovie(FavoritesMovieEntity movie);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateMovie(FavoritesMovieEntity movie);

    @Delete
    void deleteMovie(FavoritesMovieEntity movie);

    @Query("SELECT * FROM favoritesMovies WHERE idForMovie == :id")
    FavoritesMovieEntity loadMovieById(int id);

}
