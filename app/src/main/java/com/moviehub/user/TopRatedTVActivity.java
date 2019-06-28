package com.moviehub.user;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.moviehub.PopularTVAdapter;
import com.moviehub.R;
import com.moviehub.TopratedTVAdapter;
import com.moviehub.url.url;

import java.util.List;

import Interface.MovieAPI;
import model.Movie;
import model.PopularTV;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopRatedTVActivity extends AppCompatActivity {
    private RecyclerView topratedVrecyclerView;
    private List<Movie> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_top_rated_tv );

        topratedVrecyclerView=findViewById( R.id.moviesRecylerView );
        MovieAPI movieAPI = url.getInstance().create( MovieAPI.class );
        Call<List<Movie>> topratedtvcall = movieAPI.getTopratedTV(url.token);

        topratedtvcall.enqueue( new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                List<Movie> movieList = response.body();
                TopratedTVAdapter topratedTVAdapter = new TopratedTVAdapter( movieList,TopRatedTVActivity.this );
                topratedVrecyclerView.setAdapter( topratedTVAdapter );
                topratedVrecyclerView.setLayoutManager( new LinearLayoutManager( TopRatedTVActivity.this ) );
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                Toast.makeText( TopRatedTVActivity.this, "Error:"+ t.getLocalizedMessage(), Toast.LENGTH_SHORT ).show();
            }
        } );

    }
}
