package com.moviehub;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import model.Movie;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private List<Movie> movieList;
    private Context context;

    public MoviesAdapter(List<Movie> movieList, Context context) {
        this.movieList = movieList;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.movielist,viewGroup,false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder{

        ImageView imgmoviePoster;
        TextView tvmovieTitle, tvmovieRating, tvmovieDate, tvmovieGenre;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            imgmoviePoster=itemView.findViewById(R.id.imgmoviePoster);
            tvmovieTitle=itemView.findViewById(R.id.tvmovieTitle);
            tvmovieRating=itemView.findViewById(R.id.tvmovieRating);
            tvmovieGenre=itemView.findViewById(R.id.tvmovieGenre);
            tvmovieDate=itemView.findViewById(R.id.tvmovieDate);

        }
    }
}
