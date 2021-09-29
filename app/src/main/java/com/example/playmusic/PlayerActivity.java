package com.example.playmusic;

import static com.example.playmusic.HomeActivity.videoModels;

import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class PlayerActivity extends AppCompatActivity {
PlayerView playerView;
SimpleExoPlayer simpleExoPlayer;
        int position=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        playerView=findViewById(R.id.exoPlayer_movie);
        position=getIntent().getIntExtra("position",-1);
        String path=  videoModels.get(position).getPath();
       if (path!=null){
           Uri uri=Uri.parse(path);
           simpleExoPlayer=new SimpleExoPlayer.Builder(this).build();
           DataSource.Factory factory=new DefaultDataSourceFactory(this,
                   Util.getUserAgent(this,"My App Name"));
           ExtractorsFactory extractorsFactory=new DefaultExtractorsFactory();
           MediaSource mediaSource=new ProgressiveMediaSource
                   .Factory(factory,extractorsFactory).createMediaSource(uri);
           playerView.setPlayer(simpleExoPlayer);
           playerView.setKeepScreenOn(true);
           simpleExoPlayer.prepare(mediaSource);
           simpleExoPlayer.setPlayWhenReady(true);

       }
    }
}