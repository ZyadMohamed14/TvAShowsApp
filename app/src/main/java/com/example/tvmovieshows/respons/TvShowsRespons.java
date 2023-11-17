package com.example.tvmovieshows.respons;

import com.example.tvmovieshows.moviemodel.TVShow;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvShowsRespons {
    @SerializedName("page")
    private int page;
    @SerializedName("pages")
    private int total_pages;
    @SerializedName("tv_shows")
    private List<TVShow> tvShowsList;

    public int getPage() {
        return page;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public List<TVShow> getTvShowsList() {
        return tvShowsList;
    }
}
