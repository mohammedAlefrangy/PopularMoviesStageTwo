package com.example.hmod_.popularmoviesstageone.DataBase;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "favoritesMovies")
public class FavoritesMovieEntity {
    @PrimaryKey
    private int idForMovie;
    private String nameForMovie;
    private String posterForMovie;

    public FavoritesMovieEntity(int idForMovie, String nameForMovie, String posterForMovie) {
        this.idForMovie = idForMovie;
        this.nameForMovie = nameForMovie;
        this.posterForMovie = posterForMovie;
    }


    public int getIdForMovie() {
        return idForMovie;
    }

    public void setIdForMovie(int idForMovie) {
        this.idForMovie = idForMovie;
    }

    public String getNameForMovie() {
        return nameForMovie;
    }

    public void setNameForMovie(String nameForMovie) {
        this.nameForMovie = nameForMovie;
    }

    public String getPosterForMovie() {
        return posterForMovie;
    }

    public void setPosterForMovie(String posterForMovie) {
        this.posterForMovie = posterForMovie;
    }


    @Override
    public String toString() {
        return "FavMovieEntity{" +
                "movieId=" + idForMovie +
                ", movieName='" + nameForMovie + '\'' +
                ", moviePoster='" + posterForMovie + '\'' +
                '}';
    }
}
