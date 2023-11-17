package com.example.tvmovieshows.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.example.tvmovieshows.R;
import com.example.tvmovieshows.adapters.TVShowAdapter;
import com.example.tvmovieshows.databinding.ActivitySearchTvshowBinding;
import com.example.tvmovieshows.listener.TVShowsListener;
import com.example.tvmovieshows.moviemodel.TVShow;
import com.example.tvmovieshows.respons.TvShowsRespons;
import com.example.tvmovieshows.viewmodel.SearchTVShowViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class SearchTVShowActivity extends AppCompatActivity implements TVShowsListener {
    SearchTVShowViewModel viewModel;
    TVShowAdapter adapter;
    List<TVShow>tvShowList=new ArrayList<>();
    ActivitySearchTvshowBinding binding;
    int currentpage=1;
    int totalpage=1;
    private Timer timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_tvshow);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_search_tvshow);
        dointialization();
    }
private void dointialization(){
        binding.backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        viewModel=new ViewModelProvider(this).get(SearchTVShowViewModel.class);
        adapter=new TVShowAdapter(tvShowList,this);
        binding.resultsearchRC.setAdapter(adapter);
        binding.inputsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
              if(timer!=null){
                  timer.cancel();
              }
            }

            @Override
            public void afterTextChanged(Editable editable) {
              if(!editable.toString().trim().isEmpty()){
                  timer=new Timer();
                  timer.schedule(new TimerTask() {
                      @Override
                      public void run() {
                       new Handler(Looper.getMainLooper()).post(new Runnable() {
                           @Override
                           public void run() {
                               currentpage=1;
                               totalpage=1;
                               SearchTvShow(editable.toString());
                           }
                       });
                      }
                  },800);
              }
              else{
                  tvShowList.clear();
                  adapter.notifyDataSetChanged();
              }
            }
        });
        binding.resultsearchRC.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(!binding.resultsearchRC.canScrollVertically(1)){
                    if(!binding.inputsearch.getText().toString().isEmpty()){
                        if(currentpage<=totalpage){
                            currentpage++;
                            SearchTvShow(binding.inputsearch.getText().toString());
                        }
                    }

                }
            }
        });
        binding.inputsearch.requestFocus();

}
private void SearchTvShow(String query){
        viewModel.SearchTvshow(query,currentpage).observe(this, new Observer<TvShowsRespons>() {
            @Override
            public void onChanged(TvShowsRespons tvShowsRespons) {
                if(tvShowsRespons!=null){
                    totalpage=tvShowsRespons.getTotal_pages();
                    if(tvShowsRespons.getTvShowsList()!=null){
                        int old_count=tvShowList.size();
                        tvShowList.addAll(tvShowsRespons.getTvShowsList());
                        adapter.notifyItemRangeChanged(old_count,tvShowList.size());
                    }
                }
            }
        });
}
    @Override
    public void onBackPressed() {
        // code here to show dialog
        super.onBackPressed(); // optional depending on your needs
    }
    @Override
    public void onTvShowClicked(TVShow tvShow) {
        Intent intent=new Intent(getApplicationContext(), TvShowsDetailsActivity.class);

        intent.putExtra("tvshow",tvShow);
        // Start the destination activity with the Intent object
        startActivity(intent);
    }
}