package com.example.hmod_.popularmoviesstageone.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.hmod_.popularmoviesstageone.DataBase.FavoritesMovieEntity;
import com.example.hmod_.popularmoviesstageone.DataEntity.Movie;
import com.example.hmod_.popularmoviesstageone.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterForMovies extends RecyclerView.Adapter<AdapterForMovies.MyViewHolder> {


    private ArrayList<Movie> movies;
    private final Context context;
    private final OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }



    public AdapterForMovies(ArrayList<Movie> movies, Context context, OnItemClickListener onItemClickListener) {
        this.movies = movies;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Movie item = movies.get(position);
        String image = item.getPosterPath();

        Picasso.with(context).load(image).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final ImageView imageView;

        MyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.moviePoster);
            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            onItemClickListener.onListItemClick(clickedPosition);
        }
    }

    public void clear() {
        movies.clear();
        notifyDataSetChanged();
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
        notifyItemInserted(movies.size() - 1);
    }
//
//    public void addAll(List<Movie> movieList) {
//        if (movieList != null) {
//            for (Movie movie : movieList) {
//                addMovie(movie);
//            }
//        }
//    }
//
    public void addAllFavorites(List<FavoritesMovieEntity> favoritesMovieEntities) {
        for (FavoritesMovieEntity favoritesMovieEntity : favoritesMovieEntities) {
            addMovie(new Movie(favoritesMovieEntity.getOverViewForMovie(),favoritesMovieEntity.getPosterForMovie() ,
                    favoritesMovieEntity.getReleaseDateForMovie() ,favoritesMovieEntity.getNameForMovie() ,
                    favoritesMovieEntity.getVoteAverageForMovie() ,String.valueOf(favoritesMovieEntity.getIdForMovie())
                    ));
        }
    }

//    public void setTasks(ArrayList<FavoritesMovieEntity> taskEntries) {
//        movies = taskEntries;
//        notifyDataSetChanged();
//    }


}
