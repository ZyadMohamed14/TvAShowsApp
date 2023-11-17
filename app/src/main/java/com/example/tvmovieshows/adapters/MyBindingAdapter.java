package com.example.tvmovieshows.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.tvmovieshows.R;


public class MyBindingAdapter {
    @BindingAdapter("android:imageurl")
    public static void setImageURL(ImageView imageView, String URL) {
       Context context= imageView.getContext();
        RequestOptions Options = new RequestOptions().placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background);
       Glide.with(context).setDefaultRequestOptions(Options).load(URL).into(imageView);


    }
}
/*

 */
/*
   try{
            imageView.setImageAlpha(0);
            Glide.with(imageView.getContext())
                    .load(URL)
                    .dontAnimate()
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            imageView.animate().setDuration(300).alpha(1f).start();
                            return false;
                        }
                    })
                    .into(imageView);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
 */