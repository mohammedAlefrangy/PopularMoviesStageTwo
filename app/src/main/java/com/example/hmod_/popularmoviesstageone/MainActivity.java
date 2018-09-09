package com.example.hmod_.popularmoviesstageone;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import com.example.hmod_.popularmoviesstageone.Activity.DetailsMovieActivtiy;
import com.example.hmod_.popularmoviesstageone.Adapter.AdapterForMovies;
import com.example.hmod_.popularmoviesstageone.Adapter.Movie;
import com.example.hmod_.popularmoviesstageone.NetWork.NetworkUtils;
import com.example.hmod_.popularmoviesstageone.NetWork.ParssJsonObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterForMovies.OnItemClickListener {


    // Create a RecyclerView variable called recyclerView
    private RecyclerView recyclerView;
    private AdapterForMovies.OnItemClickListener onItemClickListener;
    private AdapterForMovies movieAdapter;
    private ArrayList<Movie> movies;
    private FetchMovieTask fetchMovieTask;
    private URL url;
    private NetworkUtils networkHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onItemClickListener = this;

        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);


        movies = new ArrayList<>();
        movieAdapter = new AdapterForMovies(movies, getApplicationContext(), onItemClickListener);
        recyclerView.setAdapter(movieAdapter);
        networkHandler = new NetworkUtils();
        url = networkHandler.getTopRatedMoviesULR();
        fetchMovieTask = new FetchMovieTask();
        fetchMovieTask.execute();

        NetworkUtils networkUtils = new NetworkUtils();
        URL url = networkUtils.getPopularMoviesULR();
        //this log to show if the url correct or not, the mm get url
        String mm = url.toString();
        Log.d("Mohammed", mm);

    }

    private void openMovieDetailsView(int pos) {
        Context context = MainActivity.this;
        Class detailsMovieActivtiy = DetailsMovieActivtiy.class;
        Intent intent = new Intent(context, detailsMovieActivtiy);


        intent.putExtra(DetailsMovieActivtiy.TITLE_TAG, movies.get(pos).getTitle());
        intent.putExtra(DetailsMovieActivtiy.RELEASE_DATE, movies.get(pos).getReleaseDate());
        intent.putExtra(DetailsMovieActivtiy.VOTE_AVERAGE, movies.get(pos).getVoteAverage());
        intent.putExtra(DetailsMovieActivtiy.OVERVIEW, movies.get(pos).getOverview());
        intent.putExtra(DetailsMovieActivtiy.IMAGE_POSTER, movies.get(pos).getPosterPath());

        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuItemThatSelected = item.getItemId();

        switch (menuItemThatSelected) {
            //if click on popular item in menu the app show popular movies
            case R.id.popular:
                recyclerView.setAdapter(null);
                movies = new ArrayList<>();
                movieAdapter = new AdapterForMovies(movies, getApplicationContext(), onItemClickListener);
                recyclerView.setAdapter(movieAdapter);
                networkHandler = new NetworkUtils();
                url = networkHandler.getPopularMoviesULR();
                fetchMovieTask = new FetchMovieTask();
                fetchMovieTask.execute();
                break;

            //if click on popular item in menu the app show top rated movies
            case R.id.top_rated:
                recyclerView.setAdapter(null);
                movies = new ArrayList<>();
                movieAdapter = new AdapterForMovies(movies, getApplicationContext(), onItemClickListener);
                recyclerView.setAdapter(movieAdapter);
                networkHandler = new NetworkUtils();
                url = networkHandler.getTopRatedMoviesULR();
                fetchMovieTask = new FetchMovieTask();
                fetchMovieTask.execute();

                break;
            default:
                Context context2 = MainActivity.this;
                String messagetop2 = "top_rated2 clicked";
                Toast.makeText(context2, messagetop2, Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onListItemClick(int clickedItemIndex) {
        openMovieDetailsView(clickedItemIndex);

    }

    class FetchMovieTask extends AsyncTask<String, Void, ArrayList<Movie>> implements com.example.hmod_.popularmoviesstageone.FetchMovieTask {
        ArrayList<Movie> arrayList;

        @Override
        protected ArrayList<Movie> doInBackground(String... strings) {


            String jsonResponse = null;
            try {
                jsonResponse = NetworkUtils.getResponseFromHttpUrl(url);
//                Log.d("Mohammed1", jsonResponse.toString());
                ParssJsonObject parssJsonObject = new ParssJsonObject(jsonResponse);
                arrayList = (ArrayList<Movie>) parssJsonObject.extractFromJSON();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return arrayList;
        }

        @Override
        protected void onPostExecute(ArrayList<Movie> movies1) {
            super.onPostExecute(movies1);
//            Log.d("Mohammed2", movies1.size() + "");
            movies.addAll(movies1);
//            String m = movies.get(0).toString();
//            Log.d("Mohammed3", m);

            movieAdapter.notifyDataSetChanged();

        }
    }


}

