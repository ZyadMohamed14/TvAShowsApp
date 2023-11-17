package com.example.tvmovieshows.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.tvmovieshows.reposotries.SearchTVShowsReposotrie;
import com.example.tvmovieshows.respons.TvShowsRespons;

public class SearchTVShowViewModel extends ViewModel {
    SearchTVShowsReposotrie reposotrie;
    public SearchTVShowViewModel() {
        reposotrie=new SearchTVShowsReposotrie();
    }
public LiveData<TvShowsRespons>SearchTvshow(String query,int page){
        return reposotrie.searchTVShow(query, page);

}
}
