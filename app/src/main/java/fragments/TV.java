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
public class TV extends Fragment {
    private RecyclerView tvRecylerView;
    private List<Movie> tvList;

    public TV() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movies, container, false);
        tvRecylerView =view.findViewById( R.id.moviesRecylerView );
        getAllTV();
        return view;
    }

    private void getAllTV() {
        MovieAPI movieAPI = url.getInstance().create( MovieAPI.class );
        Call<List<model.TV>> listCall = movieAPI.getAllTV(url.token);

        listCall.enqueue( new Callback<List<model.TV>>() {
            @Override
            public void onResponse(Call<List<model.TV>> call, Response<List<model.TV>> response) {
                List<model.TV> movieList = response.body();
                MoviesAdapter moviesAdapter = new MoviesAdapter( tvList,getActivity() );
                tvRecylerView.setAdapter( moviesAdapter );
                tvRecylerView.setLayoutManager( new LinearLayoutManager( getActivity() ) );
            }

            @Override
            public void onFailure(Call<List<model.TV>> call, Throwable t) {
                Toast.makeText( getActivity(), "Error:"+ t.getLocalizedMessage(), Toast.LENGTH_SHORT ).show();
            }
        } );
    }

}
