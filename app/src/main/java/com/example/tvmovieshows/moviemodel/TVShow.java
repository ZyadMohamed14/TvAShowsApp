package com.example.tvmovieshows.moviemodel;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "tvshow_table")

public class TVShow implements Serializable {
    @PrimaryKey
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String  name;
    @SerializedName("start_date")
    private String start_date;
    @SerializedName("country")
    private String country;
    @SerializedName("network")
    private String network ;
    @SerializedName("status")
    private String status ;
    @SerializedName("image_thumbnail_path")
    private String image ;
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStart_date() {
        return start_date;
    }

    public String getCountry() {
        return country;
    }

    public String getNetwork() {
        return network;
    }

    public String getStatus() {
        return status;
    }

    public String getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
