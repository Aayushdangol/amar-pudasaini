package com.moviehub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import Interface.OnGetMoviesCallback;
import model.Movie;

public class PopularMoviesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MoviesAdapter adapter;

    private MoviesRepository moviesRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_popular_movies );

        moviesRepository = MoviesRepository.getInstance();

        recyclerView = findViewById( R.id.PopularMoviesrecylerView );
        recyclerView.setLayoutManager( new LinearLayoutManager( this ) );

        moviesRepository.getMovies( new OnGetMoviesCallback() {
            @Override
            public void onSuccess(List<Movie> movies) {
                adapter = new MoviesAdapter( movies );
                recyclerView.setAdapter( adapter );
            }

            @Override
            public void onError() {
                Toast.makeText( PopularMoviesActivity.this, "Please check your internet connection", Toast.LENGTH_SHORT ).show();
            }
        } );
    }
}
