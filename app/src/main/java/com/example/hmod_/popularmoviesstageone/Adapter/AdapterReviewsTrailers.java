package com.example.hmod_.popularmoviesstageone.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hmod_.popularmoviesstageone.DataEntity.MovieReviewsEntity;
import com.example.hmod_.popularmoviesstageone.DataEntity.MovieTrailerEntity;
import com.example.hmod_.popularmoviesstageone.R;

import java.util.ArrayList;


public class AdapterReviewsTrailers extends RecyclerView.Adapter<AdapterReviewsTrailers.ReviewsHolder> {

    private ArrayList<MovieReviewsEntity> movies;
    private final Context context;



    public AdapterReviewsTrailers(Context context) {
        this.context = context;
    }


    @Override
    public ReviewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ReviewsHolder(LayoutInflater.from(context).inflate(R.layout.review_activity, parent, false));
    }

    @Override
    public void onBindViewHolder(ReviewsHolder holder, final int position) {
        MovieReviewsEntity item = movies.get(position);
        holder.review_author.setText(item.getauthor());
        holder.review_title.setText(item.getcontent());

    }

    public void setMovies(ArrayList<MovieReviewsEntity> moviesReviews) {
        movies = moviesReviews;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (movies != null) {
            return movies.size();
        } else {
            return 0;
        }


    }

    public class ReviewsHolder extends RecyclerView.ViewHolder {

        public TextView review_author;
        public TextView review_title;


        public ReviewsHolder(View itemView) {
            super(itemView);
            review_author = itemView.findViewById(R.id.review_author);
            review_title = itemView.findViewById(R.id.review_title);
        }


    }

}
