package com.example.tvmovieshows.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.tvmovieshows.reposotries.MostPopularTvShows;
import com.example.tvmovieshows.respons.TvShowsRespons;

import java.util.List;

public class MostPopularTvShowsViewModel extends ViewModel {
    MostPopularTvShows repostory;
    public MostPopularTvShowsViewModel(){
        repostory=new MostPopularTvShows();
    }
    public LiveData<TvShowsRespons> getMostPoularMovieTvshows(int page){
        return repostory.getMostPoularMovieTvshows(page);
    }

}
