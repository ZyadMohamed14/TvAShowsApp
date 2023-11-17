package com.example.tvmovieshows.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tvmovieshows.R;
import com.example.tvmovieshows.databinding.ItemContainerEpsiodeBinding;
import com.example.tvmovieshows.moviemodel.Episode;

import java.util.List;

public class EppsiodesAdapter extends RecyclerView.Adapter<EppsiodesAdapter.EpsdioeViewHolder>{

    List<Episode>episodeList;
    LayoutInflater layoutInflater;

    public EppsiodesAdapter(List<Episode> episodeList) {
        this.episodeList = episodeList;
    }

    @NonNull
    @Override
    public EpsdioeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(layoutInflater==null){
            layoutInflater=LayoutInflater.from(parent.getContext());
        }
        ItemContainerEpsiodeBinding itemContainerEpsiodeBinding= DataBindingUtil.inflate(layoutInflater, R.layout.item_container_epsiode,parent,false);
        return new EpsdioeViewHolder(itemContainerEpsiodeBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull EpsdioeViewHolder holder, int position) {
holder.bindEpsoide(episodeList.get(position));
    }

    @Override
    public int getItemCount() {
        return episodeList.size();
    }

    class EpsdioeViewHolder extends RecyclerView.ViewHolder{
        ItemContainerEpsiodeBinding binding;
        public EpsdioeViewHolder(@NonNull ItemContainerEpsiodeBinding itemContainerEpsiodeBinding ) {
            super(itemContainerEpsiodeBinding.getRoot());
            binding=itemContainerEpsiodeBinding;
        }
        public void bindEpsoide(Episode episode){
          String titel="S";
          String season=episode.getSeason();
          if(season.length()==1){
              season="0".concat(season);

          }
            String epsiodenumber=episode.getEpisode();

            if(epsiodenumber.length()==1){
                epsiodenumber="E".concat(epsiodenumber);

            }
            titel=titel.concat(season).concat(epsiodenumber);
            binding.setTital(titel);
            binding.setName(episode.getName());
            binding.setAirdate(episode.getDate());

        }
    }
}
