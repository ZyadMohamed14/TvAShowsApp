package com.example.tvmovieshows.activities;



import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.example.tvmovieshows.R;
import com.example.tvmovieshows.Utilites.TempDataHolder;
import com.example.tvmovieshows.adapters.WatchListadapter;

import com.example.tvmovieshows.databinding.ActivityWatchListBinding;
import com.example.tvmovieshows.listener.WatchListListener;
import com.example.tvmovieshows.moviemodel.TVShow;

import com.example.tvmovieshows.viewmodel.WatchListViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class WatchListActivity extends AppCompatActivity implements WatchListListener  {

    ActivityWatchListBinding watchListBinding;
    private WatchListViewModel viewModel;
    WatchListadapter watchListadapter;
   private WatchListListener listener;
   List<TVShow>tvShowList;
    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       watchListBinding= DataBindingUtil.setContentView(this,R.layout.activity_watch_list);
        dointialization();




    }
    private void dointialization(){
        viewModel= new ViewModelProvider(this).get(WatchListViewModel.class);
        tvShowList=new ArrayList<>();
        watchListBinding.iamgepresback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
      loadWatchlist();
    }
    @Override
    public void onBackPressed() {
        // code here to show dialog
        super.onBackPressed(); // optional depending on your needs
    }
    private void loadWatchlist(){
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(viewModel.
                loadWatchList().
                subscribeOn(Schedulers.computation()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(tvShows -> {
                            if (tvShowList.size()>0) {
                                tvShowList.clear();
                            }
                            tvShowList.addAll(tvShows);
                            watchListadapter=new WatchListadapter(tvShowList,this);
                            watchListadapter.notifyDataSetChanged();
                            watchListBinding.watchlistrc.setAdapter(watchListadapter);
                            compositeDisposable.dispose();

                        }
                ));
    }


    @Override
    protected void onResume() {
        super.onResume();
        if(TempDataHolder.IsWatchListUpdeted){
            loadWatchlist();
            TempDataHolder.IsWatchListUpdeted=false;
        }

    }

    @Override
    public void onTVShowClicked(TVShow tvShow) {
        Intent intent=new Intent(WatchListActivity.this, TvShowsDetailsActivity.class);

        intent.putExtra("tvshow",tvShow);
        // Start the destination activity with the Intent object
        startActivity(intent);
    }

    @Override
    public void deletFromWatchList(TVShow tvShow, int postion) {

        CompositeDisposable compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(viewModel.removeFromWatchList(tvShow).
                subscribeOn(Schedulers.computation()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(()->{
                    tvShowList.remove(postion);
                    watchListadapter.notifyItemRemoved(postion);
                    watchListadapter.notifyItemRangeChanged(postion,watchListadapter.getItemCount());
                    compositeDisposable.dispose();
                }));


    }



}
