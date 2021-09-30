package com.example.playmusic;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;



public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_PERMISSION = 123;
    LinearLayout linearLayout;
    Animation layoutAnimation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutAnimation= AnimationUtils.loadAnimation(MainActivity.this,R.anim.bottom_top);

        initView();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                linearLayout.setVisibility(View.VISIBLE);
                linearLayout.setAnimation(layoutAnimation);

            }
        },1000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                permission();
                // intent();
            }
        },5000);

    }
    private void intent(){
        Intent intent=new Intent(MainActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    private void initView() {

        linearLayout=findViewById(R.id.linearLayout);
    }
    private void permission(){
        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_CODE_PERMISSION);
        }else {
            intent();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==REQUEST_CODE_PERMISSION){
            if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(MainActivity.this,"Permission Granted",Toast.LENGTH_SHORT).show();
                intent();
            }else {
                Toast.makeText(MainActivity.this,"Permission Denied",Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_CODE_PERMISSION);
            }
        }
    }
}