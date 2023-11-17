package com.example.tvmovieshows.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.text.HtmlCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.example.tvmovieshows.R;
import com.example.tvmovieshows.Utilites.TempDataHolder;
import com.example.tvmovieshows.adapters.EppsiodesAdapter;
import com.example.tvmovieshows.adapters.ImageSliderAdapter;
import com.example.tvmovieshows.adapters.WatchListadapter;
import com.example.tvmovieshows.databinding.ActivityTvShowsDetailsBinding;
import com.example.tvmovieshows.databinding.ActivityWatchListBinding;
import com.example.tvmovieshows.databinding.LayoutEpsiodeBottonSheetBinding;
import com.example.tvmovieshows.moviemodel.TVShow;
import com.example.tvmovieshows.viewmodel.SharedViewModel;
import com.example.tvmovieshows.viewmodel.TvShowDetailsViewModel;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class TvShowsDetailsActivity extends AppCompatActivity {
    private ActivityTvShowsDetailsBinding binding;
    private TvShowDetailsViewModel viewModel;
    private LayoutEpsiodeBottonSheetBinding layoutEpsiodeBottonSheetBinding;
    private BottomSheetDialog episodebottomSheetDialog;
    private TVShow tvShow;
    private Boolean IsTvShowAvaliableInWatchList=false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tv_shows_details);
        doinitialzation();
    }

    private void doinitialzation() {


        viewModel = new ViewModelProvider(this).get(TvShowDetailsViewModel.class);

        tvShow = (TVShow) getIntent().getSerializableExtra("tvshow");

        binding.imageViewback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        CheckTvshowInWatchList();
        getTvshowsDetails();
    }
    private void CheckTvshowInWatchList(){
        CompositeDisposable compositeDisposable=new CompositeDisposable();
        compositeDisposable.add(viewModel.getTVShowFromWatchList(String.valueOf(tvShow.getId()))
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread()
                        ).subscribe(tvShow->{
                            IsTvShowAvaliableInWatchList=true;
                            binding.imagewatchlist.setImageResource(R.drawable.added);
                            compositeDisposable.dispose();
                        }

                ));
    }

    @Override
    public void onBackPressed() {
        // code here to show dialog
        super.onBackPressed(); // optional depending on your needs
    }
    private void getTvshowsDetails(){
        //  get id
        String tvshowid = String.valueOf(tvShow.getId());
        // deal with view model
        viewModel.getTvShowsDetails(tvshowid).observe(this, TvShowDetailsRespons -> {
            if (TvShowDetailsRespons.getTvShowsDetails() != null) {
                // 1 get pictures for view pager
                if (TvShowDetailsRespons.getTvShowsDetails().getPictures() != null) {
                    loadImageSlider(TvShowDetailsRespons.getTvShowsDetails().getPictures());
                }
                // 2  get URL for every image
                binding.setTVShowsImageURL(TvShowDetailsRespons.getTvShowsDetails().getImage_path());
                binding.imageTvShow.setVisibility(View.VISIBLE);
                //  3 get Descrption and deal with descrption and readmore textviews
                String des = TvShowDetailsRespons.getTvShowsDetails().getDescription();
                int mode = HtmlCompat.FROM_HTML_MODE_LEGACY;
                binding.setDescribtion(String.valueOf(HtmlCompat.fromHtml(des, mode)));
                binding.readmore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (binding.readmore.getText().toString().equals("Read More")) {
                            binding.des.setMaxLines(Integer.MAX_VALUE);
                            binding.des.setEllipsize(null);
                            binding.readmore.setText("Read Less");
                        } else {
                            binding.des.setMaxLines(4);
                            binding.des.setEllipsize(TextUtils.TruncateAt.END);
                            binding.readmore.setText("Read More");
                        }
                    }
                });
                // 4  get rating
                binding.setRating(String.format(Locale.getDefault(), "%.2f", Double.parseDouble(TvShowDetailsRespons.getTvShowsDetails().getRating())));
                // 5 get geners for every movie if it  is darama or action .....
                if (TvShowDetailsRespons.getTvShowsDetails().getGenres() != null) {
                    String text = "";
                    for (int i = 0; i < TvShowDetailsRespons.getTvShowsDetails().getGenres().length; i++) {
                        text += " " + TvShowDetailsRespons.getTvShowsDetails().getGenres()[i] + " ";
                    }
                    binding.setGener(text);
                } else {
                    binding.setGener("N/A");
                }
                //  6 get run time
                binding.setRuntime(TvShowDetailsRespons.getTvShowsDetails().getRuntime() + "Min");
                //  7 get the website of every movie
                binding.website.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(TvShowDetailsRespons.getTvShowsDetails().getUrl()));//this method need uri ;
                        startActivity(intent);
                    }
                });
                //  8 get epsiodes for every movie
                binding.episodes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (episodebottomSheetDialog == null) {
                            episodebottomSheetDialog = new BottomSheetDialog(TvShowsDetailsActivity.this);
                            layoutEpsiodeBottonSheetBinding = DataBindingUtil.inflate(LayoutInflater.from(TvShowsDetailsActivity.this)
                                    , R.layout.layout_epsiode_botton_sheet
                                    , findViewById(R.id.epsidescontainer), false);
                            episodebottomSheetDialog.setContentView(layoutEpsiodeBottonSheetBinding.getRoot());
                            layoutEpsiodeBottonSheetBinding.epsodeRC.setAdapter(new EppsiodesAdapter(
                                    TvShowDetailsRespons.getTvShowsDetails().getEpisodeList()));
                            layoutEpsiodeBottonSheetBinding.textTitel.setText(String.format("Epsiodes | %s", tvShow.getName()));
                            layoutEpsiodeBottonSheetBinding.imageclose.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    episodebottomSheetDialog.dismiss();
                                }
                            });

                        }
                        // optinal selection start
                        FrameLayout frameLayout = episodebottomSheetDialog.findViewById(com.google.android.material.R.id.design_bottom_sheet);
                        if (frameLayout != null) {
                            BottomSheetBehavior<View> bottomSheetBehavior = BottomSheetBehavior.from(frameLayout);
                            bottomSheetBehavior.setPeekHeight(Resources.getSystem().getDisplayMetrics().heightPixels);
                            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                        }
                        //optinal selection end
                        episodebottomSheetDialog.show();
                    }
                });

                //9 get watchlist
                binding.imagewatchlist.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        CompositeDisposable compositeDisposable=new CompositeDisposable();
                        if(IsTvShowAvaliableInWatchList){
                          compositeDisposable.add(viewModel.reomvoeTVShowFromWatchList(tvShow).subscribeOn(Schedulers.computation()).
                                  observeOn(AndroidSchedulers.mainThread()).subscribe(()->{
                                      IsTvShowAvaliableInWatchList=false;
                                      TempDataHolder.IsWatchListUpdeted=true;
                                      binding.imagewatchlist.setImageResource(R.drawable.added);
                                      Toast.makeText(TvShowsDetailsActivity.this, "Removed From Watch List", Toast.LENGTH_SHORT).show();
                                          }

                                  ));
                        }
                        else{

                            compositeDisposable.add(viewModel.addToWatchList(tvShow).
                                    subscribeOn(Schedulers.io()).
                                    observeOn(AndroidSchedulers.mainThread()).
                                    subscribe(() -> {
                                        binding.imagewatchlist.setImageResource(R.drawable.added);
                                        TempDataHolder.IsWatchListUpdeted=true;
                                        Toast.makeText(TvShowsDetailsActivity.this, "Added To Watch List", Toast.LENGTH_SHORT).show();
                                        compositeDisposable.dispose();
                                    })
                            );

                        }


                    }
                });

                //10 load data
                loadTVShowDetails();

            }
        });
    }
    private void loadImageSlider(String[]imagessliders){
        binding.viewpager.setOffscreenPageLimit(1);
        binding.viewpager.setAdapter(new ImageSliderAdapter(imagessliders));
        binding.viewpager.setVisibility(View.VISIBLE);
      //  binding.viewfadding.setVisibility(View.VISIBLE);

        setUpSliderIndicator(imagessliders.length);
        binding.viewpager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentSliderUpIndicator(position);
            }
        });
    }
    private void setUpSliderIndicator(int count){
        ImageView []indicators=new ImageView[count];
        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8,0,8,0);
        for (int i=0;i<indicators.length;i++){
            indicators[i]=new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.background_silder_inactive));
            indicators[i].setLayoutParams(layoutParams);
            binding.sliderindicator.addView(indicators[i]);
        }
        binding.sliderindicator.setVisibility(View.VISIBLE);
        setCurrentSliderUpIndicator(0);
    }
    private void setCurrentSliderUpIndicator(int postion){
        int childcount=binding.sliderindicator.getChildCount();
        for(int i=0;i<childcount;i++){
            ImageView imageView=(ImageView) binding.sliderindicator.getChildAt(i);
            if(i==postion){
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.background_silder_indactor_active));
            }
            else{
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.background_silder_inactive));
            }
        }
    }
    private void loadTVShowDetails(){
        binding.setName(tvShow.getName());
        binding.setNetwork(tvShow.getNetwork());
        binding.setStardate(tvShow.getStart_date());
        binding.setStatus(tvShow.getStatus());

    }
}
// format code Ctrl+Alit +L
