package com.example.playmusic;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;


public class MusicService extends Service {
    private MediaPlayer mediaPlayer;
    public MusicService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        int a= intent.getIntExtra("nalini",0);
        mediaPlayer=MediaPlayer.create(this,a);


        return new ServiceBinder();

        //  throw new UnsupportedOperationException("Not yet implemented");
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
            mediaPlayer.pause();
        }
    }
    public void onPlay(){
        if (!mediaPlayer.isPlaying()) {

            mediaPlayer.start();
        }
    }
    public void onStop(){
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
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