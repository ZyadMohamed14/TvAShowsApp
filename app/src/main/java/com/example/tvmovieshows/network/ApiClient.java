package com.example.tvmovieshows.network;

import androidx.lifecycle.MutableLiveData;

import com.example.tvmovieshows.respons.TvShowsRespons;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofit;


    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            //https://static.episodate.com/
            //https://www.episodate.com/api/
            retrofit = new Retrofit.Builder().baseUrl("https://www.episodate.com/api/").
                    addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }

}
