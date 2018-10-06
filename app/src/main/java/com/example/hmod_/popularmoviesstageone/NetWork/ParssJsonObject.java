package com.example.hmod_.popularmoviesstageone.NetWork;


import com.example.hmod_.popularmoviesstageone.DataEntity.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ParssJsonObject {
    private final String json;

    public ParssJsonObject(String jsonObject) {
        this.json = jsonObject;
    }

    public List<Movie> extractFromJSON() {
        ArrayList<Movie> movies = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray resultsJsonObject = jsonObject.getJSONArray("results");
            for (int i = 0; i < resultsJsonObject.length(); i++) {
                Movie movie = new Movie();
                JSONObject movieJsonObj = resultsJsonObject.getJSONObject(i);
                movie.setVoteAverage(Double.valueOf(movieJsonObj.get("vote_average").toString()));
                movie.setTitle(movieJsonObj.get("title").toString());
                movie.setReleaseDate(movieJsonObj.get("release_date").toString());
                movie.setPosterPath("https://image.tmdb.org/t/p/w500" + movieJsonObj.get("poster_path").toString());
                movie.setOverview(movieJsonObj.get("overview").toString());
                movie.setid(movieJsonObj.get("id").toString());

                movies.add(movie);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movies;
    }
}
