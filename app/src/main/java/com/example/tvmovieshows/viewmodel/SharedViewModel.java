package com.example.tvmovieshows.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tvmovieshows.moviemodel.TVShow;

public class SharedViewModel extends AndroidViewModel {
    private MutableLiveData<TVShow> selected = new MutableLiveData<TVShow> ();

    public SharedViewModel (Application application) {
        super (application);
    }

    public void select (TVShow tvShow) {
        selected.setValue (tvShow);
    }

    public LiveData<TVShow> getSelected () {
        return selected;
    }
}

