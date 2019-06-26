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
import java.util.HashMap;
import java.util.Map;

import Interface.MovieAPI;
import model.ImageResponse;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Url;

public class AdminMoviesActivity extends AppCompatActivity {
    private ImageView imgadminmovieposter;
    private EditText etadminMovieTitle, etadminMovieGenre, etadminMovieSynopsis, etadminMovieRating, etadminMovieDate;
    private Button btnadminaddMovie;
    String imagePath;
    String imageName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_admin_movies );

        imgadminmovieposter=findViewById( R.id.imgadminmovieposter );
        etadminMovieTitle=findViewById( R.id.etadminMovieTitle );
        etadminMovieGenre=findViewById( R.id.etadminMovieGenre );
        etadminMovieSynopsis=findViewById( R.id.etadminMovieSynopsis );
        etadminMovieRating=findViewById( R.id.etadminMovieRating );
        etadminMovieDate=findViewById( R.id.etadminMovieDate );
        btnadminaddMovie=findViewById( R.id.btnadminaddMovie );
        
        btnadminaddMovie.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddMovies();
            }
        } );

        imgadminmovieposter.setOnClickListener( new View.OnClickListener() {
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
            imgadminmovieposter.setImageBitmap(myBitmap);
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
        Call<ImageResponse> responseBodyCall = movieAPI.uploadImage( url.token,body );

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


    private void AddMovies() {
        SaveImageOnly();
        String title = etadminMovieTitle.getText().toString();
        String genre = etadminMovieGenre.getText().toString();
        String synopsis = etadminMovieSynopsis.getText().toString();
        String rating = etadminMovieRating.getText().toString();
        String date = etadminMovieDate.getText().toString();

//        Map<String, String>map = new HashMap<>(  );
//        map.put( "title",title );
//        map.put( "genre",genre );
//        map.put( "synopsis",synopsis );
//        map.put( "rating",rating );
//        map.put( "date",date );

        MovieAPI movieAPI = url.getInstance().create( MovieAPI.class );
        Call<Void> movieCall = movieAPI.addMovies( url.token, title,genre,rating,synopsis,date,imageName );

        movieCall.enqueue( new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()){
                    Toast.makeText( AdminMoviesActivity.this, "code"+response.code(), Toast.LENGTH_SHORT ).show();
                    return;
                }
                Toast.makeText( AdminMoviesActivity.this, "Movie Sucessfully Added", Toast.LENGTH_SHORT ).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

                Toast.makeText( AdminMoviesActivity.this, "Error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT ).show();
            }
        } );
    }
}
