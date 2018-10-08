package com.example.hmod_.popularmoviesstageone.DataBase;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class ViewModel extends AndroidViewModel {

    private LiveData<List<FavoritesMovieEntity>> favoritesMovieList ;

    public ViewModel(@NonNull Application application) {
        super(application);
        FavoritesMoviesDatabase database = FavoritesMoviesDatabase.getsInstance(this.getApplication());
        favoritesMovieList = database.favoritesMovieDao().loadAllMovies();
    }

    public LiveData<List<FavoritesMovieEntity>> getTasks() {
        return favoritesMovieList;
    }
}
