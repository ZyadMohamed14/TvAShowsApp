package com.example.tvmovieshows.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tvmovieshows.R;
import com.example.tvmovieshows.databinding.ItemContainerTvShowBinding;
import com.example.tvmovieshows.listener.WatchListListener;
import com.example.tvmovieshows.moviemodel.TVShow;

import java.util.List;

public class WatchListadapter extends RecyclerView.Adapter<WatchListadapter.WatchListViewHolder> {
    List<TVShow>WatchList;
    LayoutInflater layoutInflater;
    WatchListListener listener;

    public WatchListadapter(List<TVShow> watchList, WatchListListener listener) {
        WatchList = watchList;
        this.listener = listener;
    }

    public WatchListadapter(List<TVShow> watchList) {
        WatchList = watchList;
    }

    @NonNull
    @Override
    public WatchListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(layoutInflater==null){
            layoutInflater=LayoutInflater.from(parent.getContext());
        }
        ItemContainerTvShowBinding itemContainerTvShowBinding= DataBindingUtil.inflate(layoutInflater, R.layout.item_container_tv_show,parent,false);

        return new WatchListViewHolder(itemContainerTvShowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull WatchListViewHolder holder, int position) {
      holder.bindTVWatchList(WatchList.get(position));
    }

    @Override
    public int getItemCount() {
        return WatchList.size();
    }

    class WatchListViewHolder extends RecyclerView.ViewHolder{
        ItemContainerTvShowBinding itemContainerTvShowBinding;
        public WatchListViewHolder (ItemContainerTvShowBinding itemContainerTvShowBinding){
            super(itemContainerTvShowBinding.getRoot());
            this.itemContainerTvShowBinding=itemContainerTvShowBinding;
        }
        public void bindTVWatchList(TVShow tvShow){
            itemContainerTvShowBinding.setTvshows(tvShow);
            itemContainerTvShowBinding.executePendingBindings();
            itemContainerTvShowBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onTVShowClicked(tvShow);
                }
            });
            itemContainerTvShowBinding.iamgedelelt.setOnClickListener(view -> listener.deletFromWatchList(tvShow,getAdapterPosition()));
            itemContainerTvShowBinding.iamgedelelt.setVisibility(View.VISIBLE);
        }

    }
}

/*
TVShowDao

 */