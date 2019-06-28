package fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.moviehub.MoviesAdapter;
import com.moviehub.R;
import com.moviehub.url.url;

import java.util.List;

import Interface.MovieAPI;
import model.Movie;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Movies extends Fragment{
    private RecyclerView moviesRecylerView;
    private List<Movie> movieList;

    public Movies() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movies, container, false);
        moviesRecylerView =view.findViewById( R.id.moviesRecylerView );
        getAllMovies();
        return view;
    }

    private void getAllMovies() {
        MovieAPI movieAPI =url.getInstance().create( MovieAPI.class );
        Call<List<Movie>> listCall = movieAPI.getAllMovie(url.token);

        listCall.enqueue( new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                List<Movie> movieList = response.body();
                MoviesAdapter moviesAdapter = new MoviesAdapter( movieList,getActivity() );
                moviesRecylerView.setAdapter( moviesAdapter );
                moviesRecylerView.setLayoutManager( new LinearLayoutManager( getActivity() ) );
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                Toast.makeText( getActivity(), "Error:"+ t.getLocalizedMessage(), Toast.LENGTH_SHORT ).show();
            }
        } );
    }
}