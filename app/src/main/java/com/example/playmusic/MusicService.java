package com.example.playmusic;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;

import java.io.IOException;


public class MusicService extends Service {
    private MediaPlayer mediaPlayer;
    public MusicService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        String a= intent.getStringExtra("nalini").toString();

         mediaPlayer = new MediaPlayer();

        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        try {
            mediaPlayer.setDataSource(a);

            mediaPlayer.prepare();


        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ServiceBinder();


    }

    @Override
    public void onCreate() {
        super.onCreate();


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();

    }

    public void onPause(){
        if (mediaPlayer.isPlaying()){
            mediaPlayer.stop();
           // mediaPlayer.release();
           // mediaPlayer.reset();
        }
    }
    public void onPlay(){
       if (!mediaPlayer.isPlaying()) {

            mediaPlayer.start();
       }
    }
    public void onStop(){
        if (mediaPlayer.isPlaying()) {

            //onDestroy();
        }
    }

    public class ServiceBinder extends Binder {
        public MusicService getMusicService(){
            return MusicService.this;
        }

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        return super.onStartCommand(intent, flags, startId);
    }
}