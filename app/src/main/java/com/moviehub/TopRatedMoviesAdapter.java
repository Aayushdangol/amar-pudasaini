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

import model.PopularMovie;
import model.TopRatedMovie;

public class TopRatedMoviesAdapter extends RecyclerView.Adapter<TopRatedMoviesAdapter.topratedMovieHolder> {
    private List<TopRatedMovie> topRatedMovieList;
    private Context context;

    public TopRatedMoviesAdapter(List<TopRatedMovie> topRatedMovieList, Context context) {
        this.topRatedMovieList = topRatedMovieList;
        this.context = context;
    }

    @NonNull
    @Override
    public TopRatedMoviesAdapter.topratedMovieHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from( viewGroup.getContext() )
                .inflate( R.layout.item_movie,viewGroup,false );
        return new topratedMovieHolder( view );
    }

    private void StrictMode(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }


    @Override
    public void onBindViewHolder(@NonNull TopRatedMoviesAdapter.topratedMovieHolder topratedMovieHolder, int i) {
        final TopRatedMovie topRatedMovie = topRatedMovieList.get( i );
        String imgPath = url.BASE_URL + "uploads/"+ topRatedMovie.getImage();
        StrictMode();

        try{
            URL url = new URL(imgPath);
            topratedMovieHolder.imgmoviePoster.setImageBitmap( BitmapFactory.decodeStream((InputStream) url.getContent()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        topratedMovieHolder.tvmovieTitle.setText( topRatedMovie.getTitle() );
        topratedMovieHolder.tvmovieRating.setText( topRatedMovie.getRating() );
        topratedMovieHolder.tvmovieDate.setText( topRatedMovie.getReleaseDate() );
        topratedMovieHolder.tvmovieGenre.setText( topRatedMovie.getGenre() );

        topratedMovieHolder.imgmoviePoster.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("image",topRatedMovie.getImage());
                intent.putExtra("title",topRatedMovie.getTitle());
                intent.putExtra("genre",topRatedMovie.getGenre());
                intent.putExtra("synopsis",topRatedMovie.getSynopsis());
                intent.putExtra("rating",topRatedMovie.getRating());
                intent.putExtra("date",topRatedMovie.getReleaseDate());
                context.startActivity(intent);
            }
        } );

    }

    @Override
    public int getItemCount() {
        return topRatedMovieList.size();
    }

    public class topratedMovieHolder extends RecyclerView.ViewHolder{
        ImageView imgmoviePoster;
        TextView tvmovieTitle, tvmovieRating, tvmovieDate, tvmovieGenre;

        public topratedMovieHolder(@NonNull View itemView) {
            super( itemView );
            imgmoviePoster=itemView.findViewById(R.id.imgmoviePoster);
            tvmovieTitle=itemView.findViewById(R.id.tvmovieTitle);
            tvmovieRating=itemView.findViewById(R.id.tvmovieRating);
            tvmovieGenre=itemView.findViewById(R.id.tvmovieGenre);
            tvmovieDate=itemView.findViewById(R.id.tvmovieDate);
        }
    }
}
