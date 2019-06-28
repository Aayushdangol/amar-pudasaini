package com.moviehub.user;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.moviehub.PopularMoviesAdapter;
import com.moviehub.R;
import com.moviehub.TopRatedMoviesAdapter;
import com.moviehub.url.url;

import java.util.List;

import Interface.MovieAPI;
import model.PopularMovie;
import model.TopRatedMovie;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopRatedMoviesActivity extends AppCompatActivity {
    private RecyclerView topratedmoviesrecyclerView;
    private List<TopRatedMovie> topratedlistmovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_top_rated_movies );

        topratedmoviesrecyclerView=findViewById( R.id.moviesRecylerView );

        MovieAPI movieAPI = url.getInstance().create( MovieAPI.class );
        Call<List<TopRatedMovie>> topratedmoviecall = movieAPI.getTopRatedMovie(url.token);

        topratedmoviecall.enqueue( new Callback<List<TopRatedMovie>>() {
            @Override
            public void onResponse(Call<List<TopRatedMovie>> call, Response<List<TopRatedMovie>> response) {
                List<TopRatedMovie> topRatedMovieList = response.body();
                TopRatedMoviesAdapter topRatedMoviesAdapter = new TopRatedMoviesAdapter( topratedlistmovie,TopRatedMoviesActivity.this );
                topratedmoviesrecyclerView.setAdapter( topRatedMoviesAdapter );
                topratedmoviesrecyclerView.setLayoutManager( new LinearLayoutManager( TopRatedMoviesActivity.this ) );
            }

            @Override
            public void onFailure(Call<List<TopRatedMovie>> call, Throwable t) {
                Toast.makeText( TopRatedMoviesActivity.this, "Error:"+ t.getLocalizedMessage(), Toast.LENGTH_SHORT ).show();
            }
        } );
    }
}
