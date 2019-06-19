package fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.moviehub.MainActivity;
import com.moviehub.MoviesAdapter;
import com.moviehub.MoviesRepository;
import com.moviehub.R;
import com.moviehub.ViewPagerActivity;

import java.util.List;

import Interface.OnGetMoviesCallback;
import model.Movie;

public class Home extends Fragment{
    private RecyclerView movieslist;
    private MoviesAdapter adapter;
    private MoviesRepository moviesRepository;

    public Home() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_home, container, false);

        moviesRepository = MoviesRepository.getInstance();

        movieslist = view.findViewById(R.id.recyclerView);
        //movieslist.setLayoutManager(new LinearLayoutManager(this));

        moviesRepository.getMovies( new OnGetMoviesCallback() {
            @Override
            public void onSuccess(List<Movie> movies) {
                adapter = new MoviesAdapter(movies);
                movieslist.setAdapter( adapter );
            }

            @Override
            public void onError() {

               Toast.makeText( getContext(), "Please Check your Internet Connection", Toast.LENGTH_SHORT ).show();
            }
        } );

        return null;
    }

}
