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

public class PopularMoviesAdapter extends RecyclerView.Adapter<PopularMoviesAdapter.popularMovieHolder> {
    private List<PopularMovie> popularMovieList;
    private Context context;

    public PopularMoviesAdapter(List<PopularMovie> popularMovieList, Context context) {
        this.popularMovieList = popularMovieList;
        this.context = context;
    }

    @NonNull
    @Override
    public PopularMoviesAdapter.popularMovieHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from( viewGroup.getContext() )
                .inflate( R.layout.item_movie,viewGroup,false );
        return new popularMovieHolder( view );
    }

    private void StrictMode(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }
    @Override
    public void onBindViewHolder(@NonNull PopularMoviesAdapter.popularMovieHolder popularMovieHolder, int i) {
        final PopularMovie popularMovie = popularMovieList.get( i );
        String imgPath = url.BASE_URL + "uploads/"+ popularMovie.getImage();
        StrictMode();

        try{
            URL url = new URL(imgPath);
            popularMovieHolder.imgmoviePoster.setImageBitmap( BitmapFactory.decodeStream((InputStream) url.getContent()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        popularMovieHolder.tvmovieTitle.setText( popularMovie.getTitle() );
        popularMovieHolder.tvmovieRating.setText( popularMovie.getRating() );
        popularMovieHolder.tvmovieDate.setText( popularMovie.getReleaseDate() );
        popularMovieHolder.tvmovieGenre.setText( popularMovie.getGenre() );

        popularMovieHolder.imgmoviePoster.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("image",popularMovie.getImage());
                intent.putExtra("title",popularMovie.getTitle());
                intent.putExtra("genre",popularMovie.getGenre());
                intent.putExtra("synopsis",popularMovie.getSynopsis());
                intent.putExtra("rating",popularMovie.getRating());
                intent.putExtra("date",popularMovie.getReleaseDate());
                context.startActivity(intent);
            }
        } );

    }

    @Override
    public int getItemCount() {
        return popularMovieList.size();
    }

    public class popularMovieHolder extends RecyclerView.ViewHolder{
        ImageView imgmoviePoster;
        TextView tvmovieTitle, tvmovieRating, tvmovieDate, tvmovieGenre;


        public popularMovieHolder(@NonNull View itemView) {
            super(itemView);
            imgmoviePoster=itemView.findViewById(R.id.imgmoviePoster);
            tvmovieTitle=itemView.findViewById(R.id.tvmovieTitle);
            tvmovieRating=itemView.findViewById(R.id.tvmovieRating);
            tvmovieGenre=itemView.findViewById(R.id.tvmovieGenre);
            tvmovieDate=itemView.findViewById(R.id.tvmovieDate);
        }
    }
}
