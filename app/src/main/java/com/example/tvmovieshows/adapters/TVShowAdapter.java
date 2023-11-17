package com.example.tvmovieshows.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tvmovieshows.listener.TVShowsListener;
import com.example.tvmovieshows.moviemodel.TVShow;
import com.example.tvmovieshows.R;
import com.example.tvmovieshows.databinding.ItemContainerTvShowBinding;

import java.util.List;

public class TVShowAdapter extends RecyclerView.Adapter<TVShowAdapter.TvShowsViewHolder>{
    private  List<TVShow> tvShowsList;
    private LayoutInflater layoutInflater;
    private TVShowsListener listener;

    private  Context context;

    public TVShowAdapter(List<TVShow> tvShowsList, TVShowsListener listener) {
        this.tvShowsList = tvShowsList;
        this.listener = listener;
    }

    public void setTvShowsList(List<TVShow> tvShowsList) {
        this.tvShowsList = tvShowsList;
    }

    public TVShowAdapter(List<TVShow> tvShowsList) {
        this.tvShowsList = tvShowsList;
    }


    @NonNull
    @Override
    public TvShowsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ItemContainerTvShowBinding itemContainerTvShowBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_container_tv_show, parent, false);
        return new TvShowsViewHolder(itemContainerTvShowBinding);

    }

    @Override
    public void onBindViewHolder(@NonNull TvShowsViewHolder holder, int position) {

        holder.bindTv(tvShowsList.get(position));
    }

    @Override
    public int getItemCount() {
        return tvShowsList.size();
    }

     class TvShowsViewHolder extends RecyclerView.ViewHolder {
        private ItemContainerTvShowBinding itemContainerTvShowBinding;

        public TvShowsViewHolder(ItemContainerTvShowBinding itemContainerTvShowBinding) {
            super(itemContainerTvShowBinding.getRoot());
            this.itemContainerTvShowBinding = itemContainerTvShowBinding;
        }// new version

        public void bindTv(TVShow tvShows) {
            itemContainerTvShowBinding.setTvshows(tvShows);
            itemContainerTvShowBinding.executePendingBindings();
            itemContainerTvShowBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onTvShowClicked(tvShows);
                }
            });
        }




    }

}
