package com.example.tvmovieshows.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tvmovieshows.R;
import com.example.tvmovieshows.databinding.ItemContainerSilderImageBinding;
import com.example.tvmovieshows.databinding.ItemContainerTvShowBinding;

public class ImageSliderAdapter extends RecyclerView.Adapter<ImageSliderAdapter.ImageViewHolder>{
    private String[]silderimage;
    private LayoutInflater layoutInflater;

    public ImageSliderAdapter(String[] silderimage) {
        this.silderimage = silderimage;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ItemContainerSilderImageBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_container_silder_image, parent, false);
        return new ImageViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
holder.bindTv(silderimage[position]);
    }

    @Override
    public int getItemCount() {
        return silderimage.length;
    }

    class ImageViewHolder extends RecyclerView.ViewHolder{
        private ItemContainerSilderImageBinding binding;
        public ImageViewHolder(ItemContainerSilderImageBinding binding) {
            super(binding.getRoot());
            this.binding=binding;

        }
        public void bindTv(String imageURL){
          binding.setImageURL(imageURL);
        }
    }
}
