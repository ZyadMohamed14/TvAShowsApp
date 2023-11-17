package com.example.tvmovieshows.moviemodel;

import com.google.gson.annotations.SerializedName;

public class Episode {
    /*
   "season": 1,
           "episode": 10,
           "name": "Burned",
           "air_date": "2013-01-17 01:00:00"
    */
    @SerializedName("season")
    private String season;
    @SerializedName("name")
    private String name;
    @SerializedName("episode")
    private String episode;
    @SerializedName("air_date")
    private String date;

    public String getSeason() {
        return season;
    }

    public String getName() {
        return name;
    }

    public String getEpisode() {
        return episode;
    }

    public String getDate() {
        return date;
    }
}
