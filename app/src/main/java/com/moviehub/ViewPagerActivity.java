package com.moviehub;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.moviehub.user.GetProfile;

import adapter.ViewPagerAdapter;
import fragments.Recommended;
import fragments.Movies;
import fragments.TV;

public class ViewPagerActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;

    private ImageButton btnProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        tabLayout=findViewById(R.id.tabId);
        viewPager=findViewById(R.id.viewpager);

        btnProfile=findViewById( R.id. profile);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Recommended(),"Recommended");
        adapter.addFragment(new Movies(),"Movies");
        adapter.addFragment(new TV(),"TV");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        btnProfile.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( ViewPagerActivity.this, GetProfile.class );
                startActivity( intent );
            }
        } );

    }}
