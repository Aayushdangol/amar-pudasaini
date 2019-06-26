package com.moviehub.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.moviehub.R;

public class AdminDashboardActivity extends AppCompatActivity {
    private ImageButton adminpopularMovies, adminpopularTV, admintopratedMovies, admintopratedTV, adminMovies, adminTV, adminLogut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_admin_dashboard );

        adminpopularMovies=findViewById( R.id.adminpopularMovies );
        adminpopularTV=findViewById( R.id.adminpopularTV );
        admintopratedMovies=findViewById( R.id.admintopRatedMovies );
        admintopratedTV=findViewById( R.id.admintopRatedTV );
        adminMovies=findViewById( R.id.adminMovies );
        adminTV=findViewById( R.id.adminTV );

        adminpopularMovies.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( AdminDashboardActivity.this, AdminPopularMoviesActivity.class );
                startActivity( intent );
            }
        } );

        adminpopularTV.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( AdminDashboardActivity.this, AdminPopularTVActivity.class );
                startActivity( intent );
            }
        } );

        admintopratedMovies.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( AdminDashboardActivity.this, AdminTopRatedMoviesActivity.class );
                startActivity( intent );
            }
        } );

        admintopratedTV.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( AdminDashboardActivity.this, AdminTopRatedTVActivity.class );
                startActivity( intent );
            }
        } );

        adminMovies.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( AdminDashboardActivity.this, AdminMoviesActivity.class );
                startActivity( intent );
            }
        } );

        adminTV.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( AdminDashboardActivity.this, AdminTVActivity.class );
                startActivity( intent );
            }
        } );

    }
}
