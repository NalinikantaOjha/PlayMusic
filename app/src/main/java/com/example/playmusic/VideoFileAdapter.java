package com.example.playmusic;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;

public class VideoFileAdapter extends RecyclerView.Adapter<VideoFileAdapter.VideoFileViewHolder> {
   static ArrayList<VideoModel> videoModels;
    private Context context;


    public VideoFileAdapter(ArrayList<VideoModel> videoModels, Context context) {
        this.videoModels = videoModels;
        this.context = context;
    }

    @NonNull
    @Override
    public VideoFileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_file_layout,parent,false);
        return new VideoFileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoFileViewHolder holder, int position) {
    holder.name.setText(videoModels.get(position).getTitle());
    Glide.with(context).load(new File(videoModels.get(position).getPath())).into(holder.thumbNail);
    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent=new Intent(context,PlayerActivity.class);
            intent.putExtra("position",position);
            context.startActivity(intent);
        }
    });
    }

    @Override
    public int getItemCount() {
        return videoModels.size();
    }

    public class VideoFileViewHolder extends RecyclerView.ViewHolder{
        TextView name,videoDuration,videoResolution;
        ImageView thumbNail,menuMore;
        public VideoFileViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.tvFileNameFile);
            videoDuration=itemView.findViewById(R.id.tvVideoDurationFile);
            videoResolution=itemView.findViewById(R.id.tvVideoResolutionFile);
            thumbNail=itemView.findViewById(R.id.imageViewThumbNileFile);
            menuMore=itemView.findViewById(R.id.imageViewMoreFile);
        }

    }
}
