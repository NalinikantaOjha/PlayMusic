package Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.playmusic.R;

import Activity.cameraFolder;
import Activity.creationFolder;
import Activity.downloadFolder;
import Activity.screenshotsFolder;
import Activity.whatsappFolder;


public class Fragmentb extends Fragment {
CardView download,whatsapp,video,creation,screenshots,camera;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_b, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        setOnClick(view);
    }

    private void setOnClick(View view) {
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), downloadFolder.class);
                startActivity(intent);
            }
        });
        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), downloadFolder.class);
                startActivity(intent);
            }
        });
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), downloadFolder.class);
                startActivity(intent);
            }
        });
        creation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), downloadFolder.class);
                startActivity(intent);
            }
        });
        screenshots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), downloadFolder.class);
                startActivity(intent);
            }
        });
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), downloadFolder.class);
                startActivity(intent);
            }
        });
    }

    private void initView(View view) {
        download=view.findViewById(R.id.downLoads);
        whatsapp=view.findViewById(R.id.whatsapp);
        video=view.findViewById(R.id.videos);
        creation=view.findViewById(R.id.creation);
        screenshots=view.findViewById(R.id.screenRecords);
        camera=view.findViewById(R.id.camera);
    }
}