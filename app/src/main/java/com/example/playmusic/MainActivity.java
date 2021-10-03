package com.example.playmusic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.SearchView;


import android.os.Bundle;
import android.os.Handler;

import android.view.animation.Animation;

import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import Fragment.FragmentActivity;

import Fragment.FavouritesFragment;
import Fragment.HomeFragment;
import Fragment.SettingsFragment;
import Fragment.VideosFragment;

public class MainActivity extends AppCompatActivity {
    private EditText searchView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                intent();
            }
        },5000);

    }
    private void intent(){
        Intent intent=new Intent(MainActivity.this, FragmentActivity.class);
        startActivity(intent);
    }




}
       /* setContentView(R.layout.activity_main2);
        searchView = findViewById(R.id.SearchBar);
        Searchbar();
    //    HomeFragment blankFragment = new HomeFragment();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new HomeFragment())
                .commit();
    }

    private void Searchbar() {
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchView.getText().toString().equals("Youtube")) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://www.youtube.com/watch?v=bzSTpdcs-EI"));
                    intent.setPackage("com.google.android.youtube");
                    startActivity(intent);
                }
            }
        });
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener navListener = new
            BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                  Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.home:
                           selectedFragment = new HomeFragment();
                            break;
                        case R.id.Videos:
                            selectedFragment = new VideosFragment();

                            break;


                            case R.id.Favourite:
                                selectedFragment = new FavouritesFragment();
                            break;

                        case R.id.Settings:
                            selectedFragment = new SettingsFragment();
                            break;
                    }


                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, selectedFragment)
                            .commit();
                    return true;
                }
            };
    }*/
