package com.example.tvmovieshows.reposotries;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tvmovieshows.network.ApiClient;
import com.example.tvmovieshows.network.ApiServices;
import com.example.tvmovieshows.respons.TvShowsRespons;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MostPopularTvShows {
    private ApiServices apiServices;
    public MostPopularTvShows(){
        apiServices= ApiClient.getRetrofit().create(ApiServices.class);
    }
    public LiveData<TvShowsRespons> getMostPoularMovieTvshows(int page){
        MutableLiveData<TvShowsRespons> myData = new MutableLiveData<>();
        apiServices.getMostPopularMovies(page).enqueue(new Callback<TvShowsRespons>() {
            @Override
            public void onResponse(@NonNull Call<TvShowsRespons> call, @NonNull Response<TvShowsRespons> response) {
                myData.setValue(response.body());

            }

            @Override
            public void onFailure(@NonNull Call<TvShowsRespons> call, @NonNull Throwable t) {
                myData.setValue(null);
            }
        });
        return myData;
    }


}
