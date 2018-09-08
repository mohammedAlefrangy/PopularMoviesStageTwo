package com.example.hmod_.popularmoviesstageone;

import java.io.IOException;
import java.net.URL;

public class AsyncTask extends android.os.AsyncTask<URL, Void, Movie[]> {
    @Override
    protected Movie[] doInBackground(URL... urls) {
        URL searchUrl = urls[0];
        String SerachResults = null;
        try {
            SerachResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SerachResults;
    }

    @Override
    protected void onPostExecute(Movie[] movies) {
        if(movies !=null && !movies.equals("")){

        }
    }
}
