package com.moviehub;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.moviehub.url.url;

import java.io.InputStream;
import java.net.URL;
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
        View view = LayoutInflater.from( viewGroup.getContext() )
                .inflate( R.layout.item_movie,viewGroup,false);
        return new MovieViewHolder( view );
    }

    private void StrictMode(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesAdapter.MovieViewHolder holder, int i) {
        final Movie movie = movieList.get( i );
        String imgPath = url.BASE_URL + "uploads/"+ movie.getImage();
        StrictMode();

        try{
            URL url = new URL(imgPath);
            holder.imgmoviePoster.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.tvmovieTitle.setText( movie.getTitle() );
        holder.tvmovieRating.setText( movie.getRating() );
        holder.tvmovieDate.setText( movie.getReleaseDate() );
        holder.tvmovieGenre.setText( movie.getGenre() );

        holder.imgmoviePoster.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("image",movie.getImage());
                intent.putExtra("title",movie.getTitle());
                intent.putExtra("genre",movie.getGenre());
                intent.putExtra("synopsis",movie.getSynopsis());
                intent.putExtra("rating",movie.getRating());
                intent.putExtra("date",movie.getReleaseDate());
                context.startActivity(intent);
            }
        } );
    }

    @Override
    public int getItemCount() {
        return movieList.size();
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
