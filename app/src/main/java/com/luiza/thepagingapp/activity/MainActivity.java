package com.luiza.thepagingapp.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.bumptech.glide.RequestManager;
import com.luiza.thepagingapp.R;
import com.luiza.thepagingapp.adapter.MoviesAdapter;
import com.luiza.thepagingapp.adapter.MoviesLoadStateAdapter;
import com.luiza.thepagingapp.databinding.ActivityMainBinding;
import com.luiza.thepagingapp.util.GridSpace;
import com.luiza.thepagingapp.util.MovieComparator;
import com.luiza.thepagingapp.util.Utils;
import com.luiza.thepagingapp.viewmodel.MovieViewModel;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {


    MovieViewModel mainActivityViewModel;
    ActivityMainBinding binding;
    MoviesAdapter moviesAdapter;

    @Inject
    RequestManager requestManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (Utils.API_KEY == null || Utils.API_KEY.isEmpty()){
            Toast.makeText(this, "Error in API Key", Toast.LENGTH_SHORT).show();
        }

        moviesAdapter = new MoviesAdapter(new MovieComparator(),requestManager);

        mainActivityViewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        initRecyclerviewAndAdapter();


        // subscribe to paging data
        mainActivityViewModel.moviePagingDataFlowable.subscribe(moviePagingData -> {
            moviesAdapter.submitData(getLifecycle(), moviePagingData);
        });
    }

    private void initRecyclerviewAndAdapter() {

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);

        binding.recyclerViewMovies.setLayoutManager(gridLayoutManager);


        binding.recyclerViewMovies.addItemDecoration(new GridSpace(2,20,true));

        binding.recyclerViewMovies.setAdapter(
                moviesAdapter.withLoadStateFooter(
                        new MoviesLoadStateAdapter(view -> {
                            moviesAdapter.retry();
                        })
                )
        );

        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return moviesAdapter.getItemViewType(position) == MoviesAdapter.LOADING_ITEM ? 1:2;
            }
        });


    }
}

