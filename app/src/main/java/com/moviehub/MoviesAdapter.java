package com.moviehub;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import model.Genre;
import model.Movie;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private List<Movie> movies;
    private List<Genre> allGenres;
//
    public MoviesAdapter(List<Movie> movies) {
        this.movies = movies;
        this.allGenres = allGenres;
    }

//    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie,parent,false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

        holder.bind(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder{

        ImageView imgmoviePoster;
        TextView tvmovieTitle, tvmovieRating, tvmovieDate, tvmovieGenre;
//
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            imgmoviePoster=itemView.findViewById(R.id.imgmoviePoster);
            tvmovieTitle=itemView.findViewById(R.id.tvmovieTitle);
            tvmovieRating=itemView.findViewById(R.id.tvmovieRating);
            tvmovieGenre=itemView.findViewById(R.id.tvmovieGenre);
            tvmovieDate=itemView.findViewById(R.id.tvmovieDate);

        }


        public void bind(Movie movie) {
            tvmovieDate.setText(movie.getReleaseDate().split("-")[0]);
            tvmovieTitle.setText(movie.getTitle());
            tvmovieRating.setText(String.valueOf(movie.getRating()));
            tvmovieGenre.setText(getGenres(movie.getGenreIds()));
        }

        private String getGenres(List<Integer> genreIds) {
            List<String> movieGenres = new ArrayList<>(  );
            for (Integer genreId : genreIds){
                for (Genre genre : allGenres){
                    if (genre.getId()==genreId){
                        movieGenres.add( genre.getName() );
                        break;
                    }
                }
            }
            return TextUtils.join( ",",movieGenres );
        }
    }

}
