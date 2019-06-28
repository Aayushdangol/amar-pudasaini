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
import model.PopularTV;

public class PopularTVAdapter extends RecyclerView.Adapter<PopularTVAdapter.popularTVHolder> {
    private List<PopularTV> popularTVList;
    private Context context;

    public PopularTVAdapter(List<PopularTV> popularTVList, Context context) {
        this.popularTVList = popularTVList;
        this.context = context;
    }

    @NonNull
    @Override
    public PopularTVAdapter.popularTVHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from( viewGroup.getContext() )
                .inflate( R.layout.item_movie,viewGroup,false );
        return new popularTVHolder( view );
    }

    private void StrictMode(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularTVAdapter.popularTVHolder popularTVHolder, int i) {
        final PopularTV popularTV = popularTVList.get( i );
        String imgPath = url.BASE_URL + "uploads/"+ popularTV.getImage();
        StrictMode();

        try{
            URL url = new URL(imgPath);
            popularTVHolder.imgmoviePoster.setImageBitmap( BitmapFactory.decodeStream((InputStream) url.getContent()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        popularTVHolder.tvmovieTitle.setText( popularTV.getTitle() );
        popularTVHolder.tvmovieRating.setText( popularTV.getRating() );
        popularTVHolder.tvmovieDate.setText( popularTV.getReleaseDate() );
        popularTVHolder.tvmovieGenre.setText( popularTV.getGenre() );

        popularTVHolder.imgmoviePoster.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("image",popularTV.getImage());
                intent.putExtra("title",popularTV.getTitle());
                intent.putExtra("genre",popularTV.getGenre());
                intent.putExtra("synopsis",popularTV.getSynopsis());
                intent.putExtra("rating",popularTV.getRating());
                intent.putExtra("date",popularTV.getReleaseDate());
                context.startActivity(intent);
            }
        } );
    }

    @Override
    public int getItemCount() {
        return popularTVList.size();
    }

    public class popularTVHolder extends RecyclerView.ViewHolder{
        ImageView imgmoviePoster;
        TextView tvmovieTitle, tvmovieRating, tvmovieDate, tvmovieGenre;

        public popularTVHolder(@NonNull View itemView) {
            super(itemView);
            imgmoviePoster=itemView.findViewById(R.id.imgmoviePoster);
            tvmovieTitle=itemView.findViewById(R.id.tvmovieTitle);
            tvmovieRating=itemView.findViewById(R.id.tvmovieRating);
            tvmovieGenre=itemView.findViewById(R.id.tvmovieGenre);
            tvmovieDate=itemView.findViewById(R.id.tvmovieDate);
        }
    }
}
