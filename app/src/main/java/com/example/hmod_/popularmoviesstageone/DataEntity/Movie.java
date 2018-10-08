package com.example.hmod_.popularmoviesstageone.DataEntity;

public class Movie {

    private String overview;
    private String posterPath;
    private String releaseDate;
    private String title;
    private Double voteAverage;
    private String id;

    public Movie() {

    }

    public Movie(String id, String title, String posterPath) {
        this.id = id;
        this.title = title;
        this.posterPath = posterPath;

    }


    public Movie(String overview, String posterPath, String releaseDate, String title, Double voteAverage, String id) {
        super();
        this.overview = overview;
        this.posterPath = posterPath;
        this.releaseDate = releaseDate;
        this.title = title;
        this.voteAverage = voteAverage;
        this.id = id;
    }


//    public String getBackdropPath() {
//        return backdropPath;
//    }
//
//    public void setBackdropPath(String backdropPath) {
//        this.backdropPath = backdropPath;
//    }
//
//    public String getHomepage() {
//        return homepage;
//    }
//
//    public void setHomepage(String homepage) {
//        this.homepage = homepage;
//    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVoteAverage() {
        return String.valueOf(voteAverage);
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getid() {
        return id;
    }

    public void setid(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.getPosterPath();
    }
}
