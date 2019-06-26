package fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.moviehub.user.PopularMoviesActivity;
import com.moviehub.user.PopularTVActivity;
import com.moviehub.R;
import com.moviehub.user.TopRatedMoviesActivity;
import com.moviehub.user.TopRatedTVActivity;

public class Recommended extends Fragment{

    private ImageButton popularMovies;
    private ImageButton popularTV;
    private ImageButton topRatedMovies;
    private ImageButton topRatedTV;

    public Recommended() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recommended, container, false);
        popularMovies = view.findViewById( R.id.popularMovies );
        popularTV = view.findViewById( R.id.popularTV );
        topRatedMovies= view.findViewById( R.id.topRatedMovies );
        topRatedTV = view.findViewById( R.id.topRatedTV );

        popularMovies.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( getActivity(), PopularMoviesActivity.class );
                startActivity( intent );
            }
        } );

        popularTV.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( getActivity(), PopularTVActivity.class );
                startActivity( intent );
            }
        } );

        topRatedTV.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( getActivity(), TopRatedTVActivity.class );
                startActivity( intent );
            }
        } );

        topRatedMovies.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( getActivity(), TopRatedMoviesActivity.class );
                startActivity( intent );
            }
        } );

        return view;
    }

}
