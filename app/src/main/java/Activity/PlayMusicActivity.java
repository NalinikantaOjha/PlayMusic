package Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.playmusic.R;
import com.gauravk.audiovisualizer.visualizer.CircleLineVisualizer;

import java.io.File;
import java.util.ArrayList;

import Fragment.FragmentActivity;

public class PlayMusicActivity extends AppCompatActivity {
    ImageView ivBack, ivShare, ivImgLogo, ivFavorite, ivAddToPlayList, ivAttributes, ivTimer, ivShuffle, ivPrevious, ivNext, ivPlaList, ivPlay, ivPause;
    TextView trackName, start, end;
    SeekBar seekBar;
    CircleLineVisualizer visualizer;

    String songName;
    public static final String EXTRA_NAME = "song_name";
    static MediaPlayer mediaPlayer = new MediaPlayer();
    int position;
    ArrayList<File> mySongs;
Thread updateSeekbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        initView();

        if (mediaPlayer.isPlaying()){
            mediaPlayer.reset();
        }
        mediaPlayers();
        seekbarUpdate();
        visualizerUpdate();
    }

    private void visualizerUpdate() {
        int audioSessionId=mediaPlayer.getAudioSessionId();
        if (audioSessionId!=-1){
            visualizer.setAudioSessionId(audioSessionId);
        }
    }

    private void seekbarUpdate() {
        updateSeekbar=new Thread(){
            @Override
            public void run() {
                int totalDuration=mediaPlayer.getDuration();
                int currPosition=0;
                while (currPosition<totalDuration){
                    try {
                        sleep(500);
                        currPosition=mediaPlayer.getCurrentPosition();
                        seekBar.setProgress(currPosition);
                    } catch (InterruptedException |IllegalStateException e) {
                        e.printStackTrace();
                    }
                }
                super.run();
            }
        };
        seekBar.setMax(mediaPlayer.getDuration());
        updateSeekbar.start();
        seekBar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.MULTIPLY);
        seekBar.getThumb().setColorFilter(getResources().getColor(R.color.black),PorterDuff.Mode.SRC_IN);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                   mediaPlayer.seekTo(seekBar.getProgress());
            }
        });

        String endTime=createTime(mediaPlayer.getDuration());
        end.setText(endTime);

        final Handler handler=new Handler();
        final int delay=1000;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                String currTime=createTime(mediaPlayer.getCurrentPosition());
                start.setText(currTime);
                handler.postDelayed(this,delay);
            }
        },delay);
    }

    private void mediaPlayers() {
        if (mediaPlayer != null) {
            Intent intent = getIntent();
            String url = intent.getStringExtra("songs");
            String track = intent.getStringExtra("songName");

            String sName = intent.getStringExtra("songName");

            Uri uri = Uri.parse(url);

            trackName.setText(track);
            mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);
            mediaPlayer.start();

        }
        ivPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    ivPause.setImageResource(R.drawable.ic_baseline_play_arrow_24);

                } else {
                    ivPause.setImageResource(R.drawable.ic_baseline_pause_24);
                    mediaPlayer.start();

                    TranslateAnimation moveAnim = new TranslateAnimation(-5, 5, -5, 5);
                    moveAnim.setInterpolator(new AccelerateInterpolator());
                    moveAnim.setDuration(600);
                    moveAnim.setFillEnabled(true);
                    moveAnim.setFillAfter(true);
                    moveAnim.setRepeatMode(Animation.REVERSE);
                    moveAnim.setRepeatCount(1);

                    ivImgLogo.startAnimation(moveAnim);
                }
            }
        });
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                ivNext.performClick();
            }
        });
        ivNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAnimation(ivImgLogo, 360f);
            }
        });
        ivPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAnimation(ivImgLogo, -360f);
            }
        });

    }

    private void initView() {
        ivBack = findViewById(R.id.ivBack);
        ivShare = findViewById(R.id.ivShare);
        ivImgLogo = findViewById(R.id.ivImagLogo);
        ivFavorite = findViewById(R.id.ivFavorite);
        ivAddToPlayList = findViewById(R.id.ivAddToPlayList);
        ivAttributes = findViewById(R.id.ivAttributes);
        ivTimer = findViewById(R.id.ivTimer);
        ivShuffle = findViewById(R.id.ivShuffle);
        ivPrevious = findViewById(R.id.ivPrevious);
        ivNext = findViewById(R.id.ivNext);
        ivPause = findViewById(R.id.PauseVideo);
//        ivPlay=findViewById(R.id.Play);
        trackName = findViewById(R.id.trackName);
        start = findViewById(R.id.durationStart);
        end = findViewById(R.id.durationEnd);
        seekBar = findViewById(R.id.seekBar);
        visualizer = findViewById(R.id.blast);


        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PlayMusicActivity.this, FragmentActivity.class);
                startActivity(intent);
            }
        });
    }

    public void startAnimation(View v, Float degree) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(ivImgLogo, "rotation", 0f, degree);
        objectAnimator.setDuration(1000);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimator);
        animatorSet.start();
    }
    public String createTime(int duration){
        String time="";
        int min=duration/1000/60;
        int sec=duration/1000%60;

        time=time+min+":";
        if (sec<10){
            time+="0";
        }
        time+=sec;
        return time;
    }
}