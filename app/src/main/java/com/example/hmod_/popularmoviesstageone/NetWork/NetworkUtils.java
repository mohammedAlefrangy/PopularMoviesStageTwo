/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.hmod_.popularmoviesstageone.NetWork;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;


public class NetworkUtils {


    private final static String TMDB_BASE_URL = "https://api.themoviedb.org/3";
    private final static String MOVIE = "/movie";
    private final static String POPULAR = "/popular";
    private final static String TOP_RATED = "/top_rated";
    private final static String VIDEOS = "/videos";
    private final static String REVIEWS = "/reviews";

    private final String API_KEY_PARAM = "api_key";
    //TODO (1) insert your api_key here
    private final String KEY = "";

    public NetworkUtils() {
    }


    public URL getPopularMoviesULR() {
        Uri builtUri = Uri.parse(TMDB_BASE_URL + MOVIE + POPULAR).buildUpon()
                .appendQueryParameter(API_KEY_PARAM, KEY)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public URL getTopRatedMoviesULR() {
        Uri builtUri = Uri.parse(TMDB_BASE_URL + MOVIE + TOP_RATED).buildUpon()
                .appendQueryParameter(API_KEY_PARAM, KEY)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public URL getVideos(String ID) {
        Uri builtUri = Uri.parse(TMDB_BASE_URL + MOVIE + "/"+ ID + VIDEOS).buildUpon()
                .appendQueryParameter(API_KEY_PARAM, KEY)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v("url", "Built Trailer URI " + url);
        return url;
    }

    public URL getReviews(String IDReviews) {
        Uri builtUri = Uri.parse(TMDB_BASE_URL + MOVIE + "/"+ IDReviews + REVIEWS).buildUpon()
                .appendQueryParameter(API_KEY_PARAM, KEY)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v("url", "Built Trailer URI " + url);
        return url;
    }

    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}