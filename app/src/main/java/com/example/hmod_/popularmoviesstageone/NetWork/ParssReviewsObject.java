package com.example.hmod_.popularmoviesstageone.NetWork;


import android.util.Log;

import com.example.hmod_.popularmoviesstageone.DataEntity.MovieReviewsEntity;
import com.example.hmod_.popularmoviesstageone.DataEntity.MovieTrailerEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ParssReviewsObject {
    private String json;
    private static final String TAG = "ParssTrailerObject";

    public ParssReviewsObject(String jsonObject) {
        this.json = jsonObject;
    }

    public ArrayList<MovieReviewsEntity> extractFromJSON() {
        ArrayList<MovieReviewsEntity> moviesReviews = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray resultsJsonObject = jsonObject.getJSONArray("results");
            for (int i = 0; i < resultsJsonObject.length() - 1; i++) {
                MovieReviewsEntity movie = new MovieReviewsEntity();
                JSONObject reviewsJsonObj = resultsJsonObject.getJSONObject(i);
                movie.setauthor(reviewsJsonObj.get("author").toString());
                movie.setcontent(reviewsJsonObj.get("content").toString());
                moviesReviews.add(movie);
                Log.d(TAG, "setauthor: " + moviesReviews.get(i).getauthor());
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d(TAG, "setauthor: " + "mohammed");
        }
        return moviesReviews;
    }
}
