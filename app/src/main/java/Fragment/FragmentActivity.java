package Fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.playmusic.MusicService;
import com.example.playmusic.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FragmentActivity extends AppCompatActivity {
    BottomNavigationView bnv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        fragmentscreens();

    }
    @SuppressLint("NonConstantResourceId")
    private void fragmentscreens() {
        getSupportFragmentManager().beginTransaction().replace(R.id.mainframe, new HomeFragment()).commit();
        initview();
        bnv.setOnNavigationItemSelectedListener(item -> {
            Fragment temp=null;
            switch (item.getItemId()) {


                case R.id.menu_home:
                    temp=new HomeFragment();

                    break;
                case R.id.menu_wish:
                    temp=new FevoriteFragment();
                    break;
                case R.id.menu_search:
                    temp=new VideosFragment();
                    break;
                case R.id.account:
                    temp=new SettingFragment();
                    break;

            }
            assert temp != null;
            getSupportFragmentManager().beginTransaction().replace(R.id.mainframe, temp).commit();
            return true;
        });

    }


    private void initview() {
        bnv = findViewById(R.id.bottomNavigation);

    }




}