package com.example.playmusic;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import android.view.animation.Animation;

import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import Fragment.FragmentActivity;


public class MainActivity extends AppCompatActivity {

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