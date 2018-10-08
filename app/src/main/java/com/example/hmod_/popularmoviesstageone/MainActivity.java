package com.example.hmod_.popularmoviesstageone;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
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
import com.example.hmod_.popularmoviesstageone.DataBase.FavoritesMovieEntity;
import com.example.hmod_.popularmoviesstageone.DataBase.FavoritesMoviesDatabase;
import com.example.hmod_.popularmoviesstageone.DataBase.ViewModel;
import com.example.hmod_.popularmoviesstageone.DataEntity.Movie;
import com.example.hmod_.popularmoviesstageone.NetWork.NetworkUtils;
import com.example.hmod_.popularmoviesstageone.NetWork.ParssJsonObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements AdapterForMovies.OnItemClickListener {


    // Create a RecyclerView variable called recyclerView
    private RecyclerView recyclerView;
    private AdapterForMovies.OnItemClickListener onItemClickListener;
    private AdapterForMovies movieAdapter;
    private ArrayList<Movie> movies;
    private FetchMovieTask fetchMovieTask;
    private URL url;
    private NetworkUtils networkHandler;
    private static final String DEBUG_TAG = "NetworkStatusExample";
    boolean isWifiConn;
    NetworkInfo networkInfo;
    ConnectivityManager connMgr;

    List<FavoritesMovieEntity> favoritesMovieEntities;
    private static final String TAG = "MainActivity";

    private String CURRUNT_STAT;
    private String POPULARE_STAT = "POPULARE";
    private String TOPRATED_STAT = "TOPRATED";
    private String FAVORITES_STAT = "FAVORITES";


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
        Log.d("movies", String.valueOf(movies));
//        networkHandler = new NetworkUtils();
//        url = networkHandler.getTopRatedMoviesULR();
//
//        isNetworkConnected();
//        if (isWifiConn == true) {
//            fetchMovieTask = new FetchMovieTask();
//            fetchMovieTask.execute();
//            NetworkUtils networkUtils = new NetworkUtils();
//            URL url = networkUtils.getPopularMoviesULR();
//            //this log to show if the url correct or not, the mm get url
//            String mm = url.toString();
//            Log.d("Mohammed", mm);
//        } else {
//            Toast.makeText(MainActivity.this, "You Should check the internt connection", Toast.LENGTH_SHORT).show();
//        }

        if (savedInstanceState != null && savedInstanceState.getString("KEY") != null) {
            Log.d(TAG, "onCreate: "+ savedInstanceState.getString("Key"));
            if (savedInstanceState.getString("KEY").equals(POPULARE_STAT)) {
                getPopularMovies();
            }
             else if (savedInstanceState.getString("KEY").equals(TOPRATED_STAT)) {
                getTopRatedMovies();
            }
            else if (savedInstanceState.getString("KEY").equals(FAVORITES_STAT)) {
                getFavoritesMovies();}
        }
        else {
            //default state
            getPopularMovies();
        }

        FavoritesMoviesDatabase mDb = FavoritesMoviesDatabase.getsInstance(getApplicationContext());

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onSaveInstanceState: " + CURRUNT_STAT);
        outState.putString("KEY", CURRUNT_STAT);
        super.onSaveInstanceState(outState);
    }

    //
    private boolean isNetworkConnected() {

        //I got this code from the site developer.android.com
        //use this link to show the wep site https://developer.android.com/training/basics/network-ops/managing
        connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        isWifiConn = networkInfo.isConnected();
        Log.d(DEBUG_TAG, "Wifi connected: " + isWifiConn);

        return isWifiConn;
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
        intent.putExtra(DetailsMovieActivtiy.MOVIE_ID, movies.get(pos).getid());

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
                getPopularMovies();
                break;

            //if click on popular item in menu the app show top rated movies
            case R.id.top_rated:
                getTopRatedMovies();
                break;
            //if click on popular item in menu the app show top rated movies
            case R.id.favorites:
                getFavoritesMovies();
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
        Log.d(TAG, "onListItemClick: " + movies.get(clickedItemIndex).getOverview());
        openMovieDetailsView(clickedItemIndex);
    }

    class FetchMovieTask extends AsyncTask<String, Void, ArrayList<Movie>> implements com.example.hmod_.popularmoviesstageone.FetchMovieTask {
        ArrayList<Movie> arrayList;

        @Override
        protected ArrayList<Movie> doInBackground(String... strings) {


            String jsonResponse = null;
            try {
                jsonResponse = NetworkUtils.getResponseFromHttpUrl(url);
                ParssJsonObject parssJsonObject = new ParssJsonObject(jsonResponse);
                arrayList = (ArrayList<Movie>) parssJsonObject.extractFromJSON();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return arrayList;
        }

        @Override
        protected void onPostExecute(ArrayList<Movie> movies1) {
            isNetworkConnected();
            if (isWifiConn == true) {
                super.onPostExecute(movies1);
                movies.addAll(movies1);
                if (movies1 != null) {
                    movieAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, "You Should check the internt connection", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void getPopularMovies() {
        CURRUNT_STAT = POPULARE_STAT;
        Log.d(TAG, "getPopularMovies: "+ CURRUNT_STAT + POPULARE_STAT);

        Log.d(TAG, "getPopularMovies: "+ "getPopularMovies");
        recyclerView.setAdapter(null);
        movies = new ArrayList<>();
        movieAdapter = new AdapterForMovies(movies, getApplicationContext(), onItemClickListener);
        recyclerView.setAdapter(movieAdapter);
        isNetworkConnected();
        if (isWifiConn == true) {
            networkHandler = new NetworkUtils();
            url = networkHandler.getPopularMoviesULR();
            fetchMovieTask = new FetchMovieTask();
            fetchMovieTask.execute();
        } else {
            Toast.makeText(MainActivity.this, "You Should check the internt connection", Toast.LENGTH_SHORT).show();
        }
    }

    private void getTopRatedMovies() {
        CURRUNT_STAT = TOPRATED_STAT;
        Log.d(TAG, "getPopularMovies: "+ CURRUNT_STAT + TOPRATED_STAT);
        recyclerView.setAdapter(null);
        movies = new ArrayList<>();
        movieAdapter = new AdapterForMovies(movies, getApplicationContext(), onItemClickListener);
        recyclerView.setAdapter(movieAdapter);
        isNetworkConnected();
        if (isWifiConn == true) {
            networkHandler = new NetworkUtils();
            url = networkHandler.getTopRatedMoviesULR();
            fetchMovieTask = new FetchMovieTask();
            fetchMovieTask.execute();
        } else {
            Toast.makeText(MainActivity.this, "You Should check the internt connection", Toast.LENGTH_SHORT).show();
        }
    }

    private void getFavoritesMovies() {
        CURRUNT_STAT = FAVORITES_STAT;
        Log.d(TAG, "getFavoritesMovies: " + CURRUNT_STAT + FAVORITES_STAT);
        ViewModel viewModel = ViewModelProviders.of(this).get(ViewModel.class);
//        LiveData<List<FavoritesMovieEntity>> favMovieEntities = FavoritesMoviesDatabase.getsInstance(this).favoritesMovieDao().loadAllMovies();
        viewModel.getTasks().observe(this, new Observer<List<FavoritesMovieEntity>>() {
            @Override
            public void onChanged(@Nullable List<FavoritesMovieEntity> favMovieEntitiy) {
               movieAdapter.clear();
                if (favMovieEntitiy == null)
                    return;
                FavoritesMovieEntity[] courses = new FavoritesMovieEntity[favMovieEntitiy.size()];
                for (int i = 0; i < favMovieEntitiy.size(); i++)
                    courses[i] = favMovieEntitiy.get(i);
                movieAdapter.addAllFavorites(favMovieEntitiy);

            }
        });
    }

}

