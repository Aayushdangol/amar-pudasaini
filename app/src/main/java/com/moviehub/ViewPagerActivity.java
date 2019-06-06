package com.moviehub;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import adapter.ViewPagerAdapter;
import fragments.Genre;
import fragments.Home;
import fragments.Movies;
import fragments.Recommended;
import fragments.TV;

public class ViewPagerActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        tabLayout=findViewById(R.id.tabId);
        viewPager=findViewById(R.id.viewpager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Home(),"Home");
        //adapter.addFragment(new Recommended(),"Popular");
        adapter.addFragment(new Movies(),"Movies");
        adapter.addFragment(new TV(),"TV");
        adapter.addFragment(new Genre(),"Genre");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }}
