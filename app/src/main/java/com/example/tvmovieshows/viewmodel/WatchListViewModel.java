package com.example.tvmovieshows.viewmodel;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.example.tvmovieshows.dao.TVShowDao;
import com.example.tvmovieshows.database.TVShowDataBase;
import com.example.tvmovieshows.moviemodel.TVShow;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;


public class WatchListViewModel extends AndroidViewModel {
    private TVShowDataBase tvShowDataBase;

    public WatchListViewModel(@NonNull Application application) {
          super(application);
        tvShowDataBase=TVShowDataBase.getTvShowDataBase(application);
    }
    public Flowable<List<TVShow>> loadWatchList(){
        return tvShowDataBase.tvShowDao().getWatchList();
    }

    public Completable removeFromWatchList(TVShow tvShow){
        return  tvShowDataBase.tvShowDao().deletFromWatchList(tvShow);
    }


}
