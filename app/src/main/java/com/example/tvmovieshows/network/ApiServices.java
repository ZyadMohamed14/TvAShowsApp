package com.example.tvmovieshows.network;

import com.example.tvmovieshows.respons.TvShowDetailsRespons;
import com.example.tvmovieshows.respons.TvShowsRespons;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServices {
    @GET("most-popular")
    Call<TvShowsRespons> getMostPopularMovies(@Query("page")int page);



    @GET("show-details")
    Call<TvShowDetailsRespons>getTvShowsDetails(@Query("q")String tvshowid);


    @GET("search")
    Call<TvShowsRespons>searchTVShow(@Query("q")String query,@Query("page")int page);


}
