package com.example.hmod_.popularmoviesstageone.NetWork;


import android.util.Log;

import com.example.hmod_.popularmoviesstageone.DataEntity.MovieTrailerEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ParssTrailerObject {
    private String json;
    private static final String TAG = "ParssTrailerObject";

    public ParssTrailerObject(String jsonObject) {
        this.json = jsonObject;
    }

    public ArrayList<MovieTrailerEntity> extractFromJSON() {

        ArrayList<MovieTrailerEntity> movies = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray resultsJsonObject = jsonObject.getJSONArray("results");
            for (int i = 0; i < resultsJsonObject.length() - 1; i++) {
                MovieTrailerEntity movie = new MovieTrailerEntity();
                JSONObject trailerJsonObj = resultsJsonObject.getJSONObject(i);
                movie.setName(trailerJsonObj.get("name").toString());
                movie.setKey(trailerJsonObj.get("key").toString());
                movies.add(movie);
                Log.d(TAG, "setKey: " + movies.get(i).getKey());

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return movies;
    }
}
