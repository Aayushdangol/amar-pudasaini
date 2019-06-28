package com.moviehub.admin;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.moviehub.R;
import com.moviehub.url.url;

import java.io.File;
import java.io.IOException;

import Interface.MovieAPI;
import model.ImageResponse;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminPopularMoviesActivity extends AppCompatActivity {
    private ImageView imgadminpopularmovieposter;
    private EditText etadminpopularMovieTitle, etadminpopularMovieGenre, etadminpopularMovieSynopsis, etadminpopularMovieRating, etadminpopularMovieDate;
    private Button btnadminaddpopularMovie;
    String imagePath;
    String imageName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_admin_popular_movies );

        imgadminpopularmovieposter=findViewById( R.id.imgadminpopularmovieposter );
        etadminpopularMovieTitle=findViewById( R.id.etadminpopularMovieTitle );
        etadminpopularMovieGenre=findViewById( R.id.etadminpopularMovieGenre );
        etadminpopularMovieSynopsis=findViewById( R.id.etadminpopularMovieSynopsis );
        etadminpopularMovieRating=findViewById( R.id.etadminpopularMovieRating );
        etadminpopularMovieDate=findViewById( R.id.etadminpopularMovieDate );
        btnadminaddpopularMovie=findViewById( R.id.btnadminaddpopularMovie );

        btnadminaddpopularMovie.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddPopularMovies();
            }
        } );

        imgadminpopularmovieposter.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrowseImage();
            }
        } );

    }

    private void BrowseImage() {
        Intent intent = new Intent( Intent.ACTION_PICK );
        intent.setType( "image/*" );
        startActivityForResult(intent,0 );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult( requestCode, resultCode, data );

        if (resultCode==RESULT_OK){
            if (data==null){
                Toast.makeText(this, "Please select a Movie Image", Toast.LENGTH_SHORT).show();
            }
        }
        Uri uri=data.getData();
        imagePath=getRealPathFromUri(uri);
        previewImage(imagePath);
    }

    private String getRealPathFromUri(Uri uri) {
        String[] projection ={MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getApplicationContext(), uri, projection,
                null, null, null);
        Cursor cursor = loader.loadInBackground();
        int colIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(colIndex);
        cursor.close();
        return result;
    }

    private void previewImage(String imagePath) {
        File imgFile = new File(imagePath);
        if (imgFile.exists()){
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            imgadminpopularmovieposter.setImageBitmap(myBitmap);
        }
    }

    private void StrictMode(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    private void SaveImageOnly(){

        File file = new File(imagePath);

        RequestBody requestBody = RequestBody.create( MediaType.parse("multipart/form-data"),file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("imageFile",file.getName(),requestBody);

        MovieAPI movieAPI = url.getInstance().create(MovieAPI.class);
        Call<ImageResponse> responseBodyCall = movieAPI.uploadPopularMoviesImage( url.token,body );

        StrictMode();
        try{
            Response<ImageResponse> imageResponseResponse = responseBodyCall.execute();
            imageName = imageResponseResponse.body().getFilename();
            Toast.makeText(this, imageName, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void AddPopularMovies(){
        SaveImageOnly();
        String title = etadminpopularMovieTitle.getText().toString();
        String genre = etadminpopularMovieGenre.getText().toString();
        String synopsis = etadminpopularMovieSynopsis.getText().toString();
        String rating = etadminpopularMovieRating.getText().toString();
        String date = etadminpopularMovieDate.getText().toString();

        MovieAPI movieAPI = url.getInstance().create( MovieAPI.class );
        Call<Void> popularmovieCall = movieAPI.addPopularMovies( url.token, title,genre,rating,synopsis,date,imageName );

        popularmovieCall.enqueue( new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()){
                    Toast.makeText( AdminPopularMoviesActivity.this, "code"+response.code(), Toast.LENGTH_SHORT ).show();
                    return;
                }
                Toast.makeText( AdminPopularMoviesActivity.this, "Movie Sucessfully Added", Toast.LENGTH_SHORT ).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText( AdminPopularMoviesActivity.this, "Error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT ).show();
            }
        } );
    }

}
