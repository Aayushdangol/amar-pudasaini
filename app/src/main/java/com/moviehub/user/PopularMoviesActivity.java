package com.moviehub.user;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.moviehub.MoviesAdapter;
import com.moviehub.PopularMoviesAdapter;
import com.moviehub.R;
import com.moviehub.url.url;

import java.util.List;

import Interface.MovieAPI;
import model.Movie;
import model.PopularMovie;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopularMoviesActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<PopularMovie> popularMovieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_popular_movies );

        recyclerView =findViewById( R.id.moviesRecylerView );

        MovieAPI movieAPI =url.getInstance().create( MovieAPI.class );
        Call<List<PopularMovie>> popularmoviecall = movieAPI.getPopularMovie(url.token);

        popularmoviecall.enqueue( new Callback<List<PopularMovie>>() {
            @Override
            public void onResponse(Call<List<PopularMovie>> call, Response<List<PopularMovie>> response) {
                List<PopularMovie> popularMovieList = response.body();
                PopularMoviesAdapter popularMoviesAdapter = new PopularMoviesAdapter( popularMovieList,PopularMoviesActivity.this );
                recyclerView.setAdapter( popularMoviesAdapter );
                recyclerView.setLayoutManager( new LinearLayoutManager( PopularMoviesActivity.this ) );
            }

            @Override
            public void onFailure(Call<List<PopularMovie>> call, Throwable t) {
                Toast.makeText( PopularMoviesActivity.this, "Error:"+ t.getLocalizedMessage(), Toast.LENGTH_SHORT ).show();
            }
        } );


    }
}
