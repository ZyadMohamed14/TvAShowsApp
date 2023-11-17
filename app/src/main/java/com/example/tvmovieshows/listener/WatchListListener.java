package com.example.tvmovieshows.listener;

import com.example.tvmovieshows.moviemodel.TVShow;

public interface WatchListListener {
    void onTVShowClicked(TVShow tvShow);
    void deletFromWatchList(TVShow tvShow,int postion);
}
