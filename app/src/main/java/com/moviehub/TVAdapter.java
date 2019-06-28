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

import model.TV;

public class TVAdapter extends RecyclerView.Adapter<TVAdapter.TVViewHolder> {
    private List<TV> tvList;
    private Context context;

    public TVAdapter(List<TV> tvList, Context context) {
        this.tvList = tvList;
        this.context = context;
    }

    @NonNull
    @Override
    public TVAdapter.TVViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from( viewGroup.getContext() )
                .inflate( R.layout.item_movie,viewGroup,false);
        return new TVAdapter.TVViewHolder( view );
    }

    private void StrictMode(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    @Override
    public void onBindViewHolder(@NonNull TVAdapter.TVViewHolder tvViewHolder, int i) {
        final TV tv = tvList.get( i );
        String imgPath = url.BASE_URL + "uploads/"+ tv.getImage();
        StrictMode();

        try{
            URL url = new URL(imgPath);
            tvViewHolder.imgmoviePoster.setImageBitmap( BitmapFactory.decodeStream((InputStream) url.getContent()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        tvViewHolder.tvmovieTitle.setText( tv.getTitle() );
        tvViewHolder.tvmovieRating.setText( tv.getRating() );
        tvViewHolder.tvmovieDate.setText( tv.getReleaseDate() );
        tvViewHolder.tvmovieGenre.setText( tv.getGenre() );

        tvViewHolder.imgmoviePoster.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("image",tv.getImage());
                intent.putExtra("title",tv.getTitle());
                intent.putExtra("genre",tv.getGenre());
                intent.putExtra("synopsis",tv.getSynopsis());
                intent.putExtra("rating",tv.getRating());
                intent.putExtra("date",tv.getReleaseDate());
                context.startActivity(intent);
            }
        } );
    }

    @Override
    public int getItemCount() {
        return tvList.size();
    }

    public class TVViewHolder extends RecyclerView.ViewHolder{
        ImageView imgmoviePoster;
        TextView tvmovieTitle, tvmovieRating, tvmovieDate, tvmovieGenre;

        public TVViewHolder(@NonNull View itemView) {
            super( itemView );
            imgmoviePoster=itemView.findViewById( R.id.imgmoviePoster);
            tvmovieTitle=itemView.findViewById(R.id.tvmovieTitle);
            tvmovieRating=itemView.findViewById(R.id.tvmovieRating);
            tvmovieGenre=itemView.findViewById(R.id.tvmovieGenre);
            tvmovieDate=itemView.findViewById(R.id.tvmovieDate);
        }
    }
}
