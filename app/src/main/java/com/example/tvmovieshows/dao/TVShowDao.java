package com.example.tvmovieshows.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.tvmovieshows.moviemodel.TVShow;

import java.util.List;


import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface TVShowDao {
    @Query("SELECT * FROM tvshow_table")
    Flowable<List<TVShow>> getWatchList();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable addToWatchList(TVShow tvShow);

    @Delete
    Completable deletFromWatchList(TVShow tvShow);
    @Query("SELECt * FROM tvshow_table WHERE id=:tvshowid")
    Flowable<TVShow>getTVShowFromWatchList(String tvshowid);


}
