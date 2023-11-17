package com.example.tvmovieshows.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tvmovieshows.R;
import com.example.tvmovieshows.listener.TVShowsListener;
import com.example.tvmovieshows.moviemodel.TVShow;
import com.example.tvmovieshows.adapters.TVShowAdapter;
import com.example.tvmovieshows.databinding.ActivityMainBinding;
import com.example.tvmovieshows.viewmodel.MostPopularTvShowsViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TVShowsListener {
    private MostPopularTvShowsViewModel viewModel;
    ActivityMainBinding binding;
    private List<TVShow> tvShowsList = new ArrayList<>();
    private TVShowAdapter adapter;
    private TVShowsListener listener;
    private int currentpage = 1;
    private int totalpages = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_main);

        doinIntialzation();
    }
    private void doinIntialzation() {
        binding.imagewatchlsit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,WatchListActivity.class);
                startActivity(intent);
            }
        });
        binding.imagesearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, SearchTVShowActivity.class);
                startActivity(intent);
            }
        });
        binding.TVShowsRC.setHasFixedSize(true);
        binding.TVShowsRC.setLayoutManager(new LinearLayoutManager(this));

                viewModel = new ViewModelProvider(this).get(MostPopularTvShowsViewModel.class);
        //  adapter = new TVShowAdapter(tvShowsList, this, this);
        adapter=new TVShowAdapter(tvShowsList,this);
        binding.TVShowsRC.setAdapter(adapter);

        binding.TVShowsRC.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (binding.TVShowsRC.canScrollVertically(1) && currentpage <= totalpages) {
                    currentpage += 1;
                    getMostPopularTvShow();

                }
            }
        });





      getMostPopularTvShow();

    }

    private void getMostPopularTvShow() {

        viewModel.getMostPoularMovieTvshows(currentpage).observe(this, mostpopularTvshowsrespons -> {
            // Toast.makeText(this, "TotalPages is"+mostpopularTvshowsrespons.getTotal_pages(), Toast.LENGTH_SHORT).show();

            if (mostpopularTvshowsrespons != null) {
                totalpages = mostpopularTvshowsrespons.getTotal_pages();
                if (mostpopularTvshowsrespons.getTvShowsList() != null) {
                    tvShowsList.addAll(mostpopularTvshowsrespons.getTvShowsList());
                    int oldcount=tvShowsList.size();
                     adapter.notifyItemRangeInserted(oldcount, tvShowsList.size());
                }
            }

        });

    }


    @Override
    public void onTvShowClicked(TVShow tvShow) {
        Intent intent=new Intent(MainActivity.this, TvShowsDetailsActivity.class);

         intent.putExtra("tvshow",tvShow);
        // Start the destination activity with the Intent object
        startActivity(intent);

    }
}
