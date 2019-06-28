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

public class AdminTopRatedTVActivity extends AppCompatActivity {
    private ImageView imgadmintopratedTVposter;
    private EditText etadmintopratedTVTitle, etadmintopratedTVGenre, etadmintopratedTVSynopsis, etadmintopratedTVRating, etadmintopratedTVDate;
    private Button btnadminaddtopratedTV;
    String imagePath;
    String imageName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_admin_top_rated_tv );

        imgadmintopratedTVposter=findViewById( R.id.imgadmintopratedTVposter );
        etadmintopratedTVTitle=findViewById( R.id.etadmintopratedTVTitle );
        etadmintopratedTVGenre=findViewById( R.id.etadmintopratedTVGenre );
        etadmintopratedTVSynopsis=findViewById( R.id.etadmintopratedTVSynopsis );
        etadmintopratedTVRating=findViewById( R.id.etadmintopratedTVRating );
        etadmintopratedTVDate=findViewById( R.id.etadmintopratedTVDate );
        btnadminaddtopratedTV=findViewById( R.id.btnadminaddtopratedTV );

        btnadminaddtopratedTV.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddTopRatedTV();
            }
        } );

        imgadmintopratedTVposter.setOnClickListener( new View.OnClickListener() {
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
            imgadmintopratedTVposter.setImageBitmap(myBitmap);
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
        Call<ImageResponse> responseBodyCall = movieAPI.uploadPopularTVImage( url.token,body );

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

    private void AddTopRatedTV(){
        SaveImageOnly();
        String title = etadmintopratedTVTitle.getText().toString();
        String genre = etadmintopratedTVGenre.getText().toString();
        String synopsis = etadmintopratedTVSynopsis.getText().toString();
        String rating = etadmintopratedTVRating.getText().toString();
        String date = etadmintopratedTVDate.getText().toString();

        MovieAPI movieAPI = url.getInstance().create( MovieAPI.class );
        Call<Void> topratedtvCall = movieAPI.addTopratedTV( url.token, title,genre,rating,synopsis,date,imageName );

        topratedtvCall.enqueue( new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()){
                    Toast.makeText( AdminTopRatedTVActivity.this, "code"+response.code(), Toast.LENGTH_SHORT ).show();
                    return;
                }
                Toast.makeText( AdminTopRatedTVActivity.this, "Movie Sucessfully Added", Toast.LENGTH_SHORT ).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText( AdminTopRatedTVActivity.this, "Error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT ).show();
            }
        } );
    }

}
