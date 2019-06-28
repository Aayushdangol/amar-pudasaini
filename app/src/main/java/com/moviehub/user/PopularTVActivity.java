package com.moviehub.user;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.moviehub.PopularMoviesAdapter;
import com.moviehub.PopularTVAdapter;
import com.moviehub.R;
import com.moviehub.url.url;

import java.util.List;

import Interface.MovieAPI;
import model.PopularMovie;
import model.PopularTV;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopularTVActivity extends AppCompatActivity {
    private RecyclerView popularTVrecyclerView;
    private List<PopularTV> popularTVList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_popular_tv );

        popularTVrecyclerView =findViewById( R.id.moviesRecylerView );

        MovieAPI movieAPI = url.getInstance().create( MovieAPI.class );
        Call<List<PopularTV>> populartvcall = movieAPI.getPopularTV(url.token);

        populartvcall.enqueue( new Callback<List<PopularTV>>() {
            @Override
            public void onResponse(Call<List<PopularTV>> call, Response<List<PopularTV>> response) {
                List<PopularTV> popularTVList = response.body();
                PopularTVAdapter popularTVAdapter = new PopularTVAdapter( popularTVList,PopularTVActivity.this );
                popularTVrecyclerView.setAdapter( popularTVAdapter );
                popularTVrecyclerView.setLayoutManager( new LinearLayoutManager( PopularTVActivity.this ) );
            }

            @Override
            public void onFailure(Call<List<PopularTV>> call, Throwable t) {
                Toast.makeText( PopularTVActivity.this, "Error:"+ t.getLocalizedMessage(), Toast.LENGTH_SHORT ).show();
            }
        } );

    }
}
