package com.example.hmod_.popularmoviesstageone.DataBase;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "movies")
public class FavoritesMovieEntity {
    @PrimaryKey
    private int movieId;
    private String movieName;
    private String moviePoster;

    public FavoritesMovieEntity(int movieId, String movieName, String moviePoster) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.moviePoster = moviePoster;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMoviePoster() {
        return moviePoster;
    }

    public void setMoviePoster(String moviePoster) {
        this.moviePoster = moviePoster;
    }

    @Override
    public String toString() {
        return "FavMovieEntity{" +
                "movieId=" + movieId +
                ", movieName='" + movieName + '\'' +
                ", moviePoster='" + moviePoster + '\'' +
                '}';
    }
}
