package com.example.playmusic;

import static com.example.playmusic.HomeActivity.videoModels;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class VideosInVideoFragment extends Fragment {

  RecyclerView recyclerView;
  View view;
  VideoFileAdapter videoFileAdapter;

    public VideosInVideoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_videos_in_video, container, false);
        recyclerView=view.findViewById(R.id.fileRecyclerView);
        if (videoModels!=null&&videoModels.size()>0){
            videoFileAdapter=new VideoFileAdapter(videoModels,getContext());
            recyclerView.setAdapter(videoFileAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        }
        return view;
    }
}