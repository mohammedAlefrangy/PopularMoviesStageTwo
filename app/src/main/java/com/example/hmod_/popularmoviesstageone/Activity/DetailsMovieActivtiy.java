package com.example.hmod_.popularmoviesstageone.Activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hmod_.popularmoviesstageone.Adapter.AdapterMoviesTrailers;
import com.example.hmod_.popularmoviesstageone.DataEntity.MovieTrailerEntity;
import com.example.hmod_.popularmoviesstageone.NetWork.NetworkUtils;
import com.example.hmod_.popularmoviesstageone.NetWork.ParssJsonObject;
import com.example.hmod_.popularmoviesstageone.NetWork.ParssTrailerObject;
import com.example.hmod_.popularmoviesstageone.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class DetailsMovieActivtiy extends AppCompatActivity implements AdapterMoviesTrailers.OnItemClickListener {

    public static final String TITLE_TAG = "title";
    public static final String RELEASE_DATE = "release_date";
    public static final String IMAGE_POSTER = "imageview_poster";
    public static final String OVERVIEW = "textview_overview";
    public static final String VOTE_AVERAGE = "vote_average";
    public static final String MOVIE_ID = "movie_id";
    private static final String KEY_MOVIE = "movie_key";
    private static final String RESULT = "results";
    private static final String NAME = "name";
    private static final String LANGUAGE = "language";
    private static final String TRAILER_URL = "https://www.youtube.com/watch?v=";

    private Context context;
    private RecyclerView trailersrecyclerView;

    String[] keyTrailer;
    String[] nameTrailer;
    String[] languageTrailer;

    private ArrayList<MovieTrailerEntity> moviestrailer;
    private AdapterMoviesTrailers movieAdapter;
    private AdapterMoviesTrailers.OnItemClickListener onItemClickListener;

    private URL trailerUrl;
    private NetworkUtils networkHandler;


    private String movieID , movieKey;
    TextView traile_name;
    ImageView play_icon;

    private static final String TAG = "DetailsMovieActivtiy";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ImageView imageview_poster = findViewById(R.id.imageview_poster);
        TextView textview_release_date = findViewById(R.id.textview_release_date);
        TextView textview_original_title = findViewById(R.id.textview_original_title);
        TextView textview_vote_average = findViewById(R.id.textview_vote_average);
        TextView textview_overview = findViewById(R.id.textview_overview);
        traile_name = findViewById(R.id.traile_name);
        play_icon = findViewById(R.id.play_icon);

        onItemClickListener = this;

        Intent intentThatStartedThisActivity = getIntent();

        if (intentThatStartedThisActivity != null) {
            String title = intentThatStartedThisActivity.getStringExtra(TITLE_TAG);
            textview_original_title.setText(title);
            String release_date = intentThatStartedThisActivity.getStringExtra(RELEASE_DATE);
            textview_release_date.setText(release_date);
            String vote_average = intentThatStartedThisActivity.getStringExtra(VOTE_AVERAGE);
            textview_vote_average.setText(vote_average);
            String view_overview = intentThatStartedThisActivity.getStringExtra(OVERVIEW);
            textview_overview.setText(view_overview);
            movieID = intentThatStartedThisActivity.getStringExtra(MOVIE_ID);


            String image_poster = intentThatStartedThisActivity.getStringExtra(IMAGE_POSTER);
            Picasso.with(context).load(image_poster).into(imageview_poster);
        }

        trailersrecyclerView = findViewById(R.id.list_trailers);
        trailersrecyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        trailersrecyclerView.setLayoutManager(layoutManager);

        moviestrailer = new ArrayList<>();
        movieAdapter = new AdapterMoviesTrailers(getApplicationContext(), onItemClickListener);
        trailersrecyclerView.setAdapter(movieAdapter);


        NetworkUtils networkUtils = new NetworkUtils();
        trailerUrl = networkUtils.getVideos(movieID);
        MovieTrailerEntity movieTrailerEntity = new MovieTrailerEntity();
        FetchTrailerTask fetchMovieTask = new FetchTrailerTask();
        fetchMovieTask.execute();


    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        openMovieTrailer(clickedItemIndex);
    }


    private void openMovieTrailer(int clickedItemIndex) {
        final String trailerURL = TRAILER_URL + moviestrailer.get(clickedItemIndex).getKey() ;
        Intent youtubeIntent = new Intent(Intent.ACTION_VIEW , Uri.parse(trailerURL));
        if (youtubeIntent.resolveActivity(getPackageManager()) != null){
            startActivity(youtubeIntent);
        }
        Log.d(TAG, "openMovieTrailer: "+ trailerURL);
    }

    class FetchTrailerTask extends AsyncTask<String, Void, ArrayList<MovieTrailerEntity>> {
        ArrayList<MovieTrailerEntity> arrayList;

        @Override
        protected ArrayList<MovieTrailerEntity> doInBackground(String... strings) {
            String jsonResponse = null;

            try {
                jsonResponse = NetworkUtils.getResponseFromHttpUrl(trailerUrl);
                Log.d(TAG, "doInBackground: " + jsonResponse);
                ParssTrailerObject parssJsonObject = new ParssTrailerObject(jsonResponse);
                arrayList = parssJsonObject.extractFromJSON();
                Log.d(TAG, "doInBackgroundfffffffffffff: " + arrayList.toString());

            } catch (IOException e) {
                e.printStackTrace();
            }
            return arrayList;
        }

        @Override
        public void onPostExecute(ArrayList<MovieTrailerEntity> movies1) {
            super.onPostExecute(movies1);
            moviestrailer.addAll(movies1);
            Log.d(TAG, "onPostExecute: " + movies1);
            movieAdapter.setMovies(moviestrailer);
//            trailerData(movies1);
//            uITrailer();
        }
    }

}
