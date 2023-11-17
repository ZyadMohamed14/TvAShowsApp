package com.example.tvmovieshows.reposotries;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tvmovieshows.network.ApiClient;
import com.example.tvmovieshows.network.ApiServices;
import com.example.tvmovieshows.respons.TvShowsRespons;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchTVShowsReposotrie {
    private ApiServices apiServices;
    public SearchTVShowsReposotrie(){
        apiServices= ApiClient.getRetrofit().create(ApiServices.class);
    }
    public LiveData<TvShowsRespons> searchTVShow(String query, int page) {
        MutableLiveData<TvShowsRespons> myData = new MutableLiveData<>();
        apiServices.searchTVShow(query, page).enqueue(new Callback<TvShowsRespons>() {
            @Override
            public void onResponse(Call<TvShowsRespons> call, Response<TvShowsRespons> response) {
                myData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<TvShowsRespons> call, Throwable t) {
        myData.setValue(null);
            }
        });
     return myData;
    }
}
