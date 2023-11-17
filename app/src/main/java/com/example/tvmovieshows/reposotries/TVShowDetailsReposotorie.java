package com.example.tvmovieshows.reposotries;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tvmovieshows.dao.TVShowDao;
import com.example.tvmovieshows.database.TVShowDataBase;
import com.example.tvmovieshows.moviemodel.TVShow;
import com.example.tvmovieshows.network.ApiClient;
import com.example.tvmovieshows.network.ApiServices;
import com.example.tvmovieshows.respons.TvShowDetailsRespons;


import io.reactivex.rxjava3.core.Completable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TVShowDetailsReposotorie {
    private ApiServices apiServices;
    TVShowDataBase dataBase;
    public TVShowDao tvShowDao;

//Application application
    public TVShowDetailsReposotorie() {
        apiServices = ApiClient.getRetrofit().create(ApiServices.class);
      // dataBase= TVShowDataBase.getTvShowDataBase(application);


    }

    public Completable addToWatchList(TVShow tvShow){
        return tvShowDao.addToWatchList(tvShow);
    }


    public LiveData<TvShowDetailsRespons> getTvShowsDetails(String tvshowid) {
        MutableLiveData<TvShowDetailsRespons> data = new MutableLiveData<>();
        apiServices.getTvShowsDetails(tvshowid).enqueue(new Callback<TvShowDetailsRespons>() {
            @Override
            public void onResponse(@NonNull Call<TvShowDetailsRespons> call, @NonNull Response<TvShowDetailsRespons> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<TvShowDetailsRespons> call, @NonNull Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}
