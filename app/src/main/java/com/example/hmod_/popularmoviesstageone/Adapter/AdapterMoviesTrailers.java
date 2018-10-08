package com.example.hmod_.popularmoviesstageone.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hmod_.popularmoviesstageone.DataEntity.MovieTrailerEntity;
import com.example.hmod_.popularmoviesstageone.R;

import java.util.ArrayList;


public class AdapterMoviesTrailers extends RecyclerView.Adapter<AdapterMoviesTrailers.TrailerHolder> {

    private ArrayList<MovieTrailerEntity> movies;
    private final Context context;
    private final AdapterMoviesTrailers.OnItemClickListener onItemClickListener;


    public interface OnItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    public AdapterMoviesTrailers( Context context, AdapterMoviesTrailers.OnItemClickListener onItemClickListener) {
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }


    @Override
    public TrailerHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new TrailerHolder(LayoutInflater.from(context).inflate(R.layout.trailer_activity, parent, false));
    }

    @Override
    public void onBindViewHolder(TrailerHolder holder, final int position) {
        MovieTrailerEntity item = movies.get(position);
        holder.name.setText(item.getName());
    }

    public void setMovies (ArrayList<MovieTrailerEntity> moviesTrailer){
        movies = moviesTrailer ;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (movies != null){
            return movies.size();
        }else {
            return 0;
        }


    }

    public class TrailerHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

//        public TextView language;
        public TextView name;


        public TrailerHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.traile_name);
//            language = itemView.findViewById(R.id.traile_language);

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            onItemClickListener.onListItemClick(clickedPosition);
        }
    }

}
