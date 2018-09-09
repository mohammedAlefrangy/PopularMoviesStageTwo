package com.example.hmod_.popularmoviesstageone.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hmod_.popularmoviesstageone.R;
import com.squareup.picasso.Picasso;

public class DetailsMovieActivtiy extends AppCompatActivity {

    public static final String TITLE_TAG = "title";
    public static final String RELEASE_DATE = "release_date";
    public static final String IMAGE_POSTER = "imageview_poster";
    public static final String OVERVIEW = "textview_overview";
    public static final String VOTE_AVERAGE = "vote_average";

    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ImageView imageview_poster = findViewById(R.id.imageview_poster);
        TextView textview_release_date = findViewById(R.id.textview_release_date);
        TextView textview_original_title = findViewById(R.id.textview_original_title);
        TextView textview_vote_average = findViewById(R.id.textview_vote_average);
        TextView textview_overview = findViewById(R.id.textview_overview);


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


            String image_poster = intentThatStartedThisActivity.getStringExtra(IMAGE_POSTER);
            Picasso.with(context).load(image_poster).into(imageview_poster);
        }
    }
}
