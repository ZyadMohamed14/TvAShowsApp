package com.example.tvmovieshows.respons;

import com.example.tvmovieshows.moviemodel.TVShowsDetails;
import com.google.gson.annotations.SerializedName;

public class TvShowDetailsRespons {
    @SerializedName("tvShow")
    private TVShowsDetails tvShowsDetails;

    public TVShowsDetails getTvShowsDetails() {
        return tvShowsDetails;
    }
}
