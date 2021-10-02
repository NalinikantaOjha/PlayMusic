package Activity;

import androidx.appcompat.app.AppCompatActivity;

import Fragment.FragmentActivity;
import Fragment.VideosFragment;
import Model.Model;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.playmusic.R;

public class VideoActivity extends AppCompatActivity {
    VideoView videoView;
    ProgressDialog progressDialog;
    String url;
    ImageView ivPause, ivBack;
    Thread updateSeekbar;
    SeekBar seekBar;
    TextView start, end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        getDataFromIntent();
        videoView = (VideoView) findViewById(R.id.videoView);
        ivPause = findViewById(R.id.PauseVideo);
        progressDialog = new ProgressDialog(VideoActivity.this);
        progressDialog.setMessage("WAIT");
        progressDialog.show();
        Uri uri = Uri.parse(url);
        videoView.setVideoURI(uri);
        videoView.start();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                progressDialog.dismiss();
            }
        });


        ivPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (videoView.isPlaying()) {
                    videoView.pause();
                    ivPause.setImageResource(R.drawable.ic_baseline_play_arrow_24);

                } else {
                    ivPause.setImageResource(R.drawable.ic_baseline_pause_24);
                    videoView.start();
                }
            }
        });

        ivBack = findViewById(R.id.ivBack);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VideoActivity.this, FragmentActivity.class);
                startActivity(intent);
            }
        });
        seekBar = findViewById(R.id.seekBar);
        start = findViewById(R.id.durationStart);
        end = findViewById(R.id.durationEnd);
        updateSeekbar = new Thread() {
            @Override
            public void run() {
                int totalDuration = videoView.getDuration();
                int currPosition = 0;
                while (currPosition < totalDuration) {
                    try {
                        sleep(500);
                        currPosition = videoView.getCurrentPosition();
                        seekBar.setProgress(currPosition);
                    } catch (InterruptedException | IllegalStateException e) {
                        e.printStackTrace();
                    }
                }
                super.run();
            }
        };
        seekBar.setMax(videoView.getDuration());
        updateSeekbar.start();
        seekBar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.MULTIPLY);
        seekBar.getThumb().setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                videoView.seekTo(seekBar.getProgress());
            }
        });

        String endTime = createTime(videoView.getDuration());
        end.setText(endTime);

        final Handler handler = new Handler();
        final int delay = 1000;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                String currTime = createTime(videoView.getCurrentPosition());
                start.setText(currTime);
                handler.postDelayed(this, delay);
            }
        }, delay);
    }

    private void getDataFromIntent() {

        Model model = (Model) getIntent().getSerializableExtra("model");
        url = model.getUrl();
    }

    public String createTime(int duration) {
        String time = "";
        int min = duration / 1000 / 60;
        int sec = duration / 1000 % 60;

        time = time + min + ":";
        if (sec < 10) {
            time += "0";
        }
        time += sec;
        return time;
    }
}


