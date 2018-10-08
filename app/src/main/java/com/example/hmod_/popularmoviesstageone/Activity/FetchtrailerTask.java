package com.example.hmod_.popularmoviesstageone.Activity;

import com.example.hmod_.popularmoviesstageone.DataEntity.Movie;

import java.util.ArrayList;

interface FetchtrailerTask {
    void onPostExecute(ArrayList<Movie> movies1);
}
