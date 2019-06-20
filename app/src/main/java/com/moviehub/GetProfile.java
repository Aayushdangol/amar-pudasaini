package com.moviehub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import de.hdodenhof.circleimageview.CircleImageView;

public class GetProfile extends AppCompatActivity {

    private CircleImageView btnProfileEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_get_profile );

        btnProfileEdit=findViewById( R.id.btnProfileEdit );

        btnProfileEdit.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( GetProfile.this, ProfileActivity.class );
                startActivity( intent );
            }
        } );


    }
}
