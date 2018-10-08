package com.example.hmod_.popularmoviesstageone.DataBase;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.example.hmod_.popularmoviesstageone.DataEntity.Movie;

@Entity(tableName = "favoritesMovies")
public class FavoritesMovieEntity {
    @PrimaryKey
    private int idForMovie;
    private String nameForMovie;
    private String posterForMovie;

    public String getOverViewForMovie() {
        return overViewForMovie;
    }

    public void setOverViewForMovie(String overViewForMovie) {
        this.overViewForMovie = overViewForMovie;
    }

    public String getReleaseDateForMovie() {
        return releaseDateForMovie;
    }

    public void setReleaseDateForMovie(String releaseDateForMovie) {
        this.releaseDateForMovie = releaseDateForMovie;
    }

    public Double getVoteAverageForMovie() {
        return voteAverageForMovie;
    }

    public void setVoteAverageForMovie(Double voteAverageForMovie) {
        this.voteAverageForMovie = voteAverageForMovie;
    }

    private String overViewForMovie;
    private String releaseDateForMovie;
    private Double voteAverageForMovie;


    public FavoritesMovieEntity(int idForMovie, String nameForMovie, String posterForMovie ,  String overViewForMovie , String releaseDateForMovie , Double voteAverageForMovie) {
        this.idForMovie = idForMovie;
        this.nameForMovie = nameForMovie;
        this.posterForMovie = posterForMovie;
        this.overViewForMovie = overViewForMovie;
        this.releaseDateForMovie = releaseDateForMovie;
        this.voteAverageForMovie = voteAverageForMovie;


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
