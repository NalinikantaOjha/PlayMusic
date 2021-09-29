package Activity;

import androidx.appcompat.app.AppCompatActivity;
import Model.Model;
import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

import com.example.playmusic.R;

public class VideoActivity extends AppCompatActivity {
    VideoView videoView;
    ProgressDialog progressDialog;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        getDataFromIntent();
        videoView=(VideoView) findViewById(R.id.videoView);
        progressDialog=new ProgressDialog(VideoActivity.this);
        progressDialog.setMessage("WAIT");
        progressDialog.show();
        Uri uri=Uri.parse(url);
        videoView.setVideoURI(uri);
        videoView.start();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                progressDialog.dismiss();
            }
        });
    }

    private void getDataFromIntent() {

        Model model=(Model)getIntent().getSerializableExtra("model");
        url=model.getUrl();
    }
}


