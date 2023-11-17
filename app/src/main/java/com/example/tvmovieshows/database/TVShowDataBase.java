package com.example.tvmovieshows.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.tvmovieshows.dao.TVShowDao;
import com.example.tvmovieshows.moviemodel.TVShow;

@Database(entities = TVShow.class,version = 1,exportSchema = false)
public abstract class TVShowDataBase extends RoomDatabase {
private static  TVShowDataBase tvShowDataBase;

    public  synchronized static TVShowDataBase getTvShowDataBase( Context context){
        if (tvShowDataBase == null) {

            tvShowDataBase = Room.databaseBuilder(context, TVShowDataBase.class, "TVShow_database").fallbackToDestructiveMigration().build();

        }
        return tvShowDataBase;
    }





public abstract TVShowDao tvShowDao();
}
/*
if(tvShowDataBase==null){
        tvShowDataBase= Room.databaseBuilder(context,
                TVShowDataBase.class,
                "tvshows").build();
    }
    return tvShowDataBase;
 */
