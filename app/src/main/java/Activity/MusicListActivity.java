package Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.playmusic.R;

import java.util.ArrayList;
import java.util.List;

import Model.ResultsDTO;

public class MusicListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list);
        List<ResultsDTO> list=new ArrayList<>();
    }
}