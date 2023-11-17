package com.example.tvmovieshows.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;


import com.example.tvmovieshows.database.TVShowDataBase;
import com.example.tvmovieshows.moviemodel.TVShow;
import com.example.tvmovieshows.reposotries.TVShowDetailsReposotorie;
import com.example.tvmovieshows.respons.TvShowDetailsRespons;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;


public class TvShowDetailsViewModel extends AndroidViewModel{
    TVShowDetailsReposotorie reposotorie;
    TVShowDataBase tvShowDataBase;


    public TvShowDetailsViewModel( @NonNull Application application) {
        super(application);
        reposotorie=new TVShowDetailsReposotorie();
        tvShowDataBase=TVShowDataBase.getTvShowDataBase(application);
    }

    public LiveData<TvShowDetailsRespons> getTvShowsDetails(String tvshowid){
        return reposotorie.getTvShowsDetails(tvshowid);
    }
    public Completable addToWatchList(TVShow tvShow){
        return tvShowDataBase.tvShowDao().addToWatchList(tvShow);
    }
    public Flowable<TVShow>getTVShowFromWatchList(String tvshowid){
        return tvShowDataBase.tvShowDao().getTVShowFromWatchList(tvshowid);
    }
    public Completable reomvoeTVShowFromWatchList(TVShow tvShow){
        return tvShowDataBase.tvShowDao().deletFromWatchList(tvShow);
    }
}
/*

 */



