package ViewHolder;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.playmusic.R;

import Interface.onItemClicked;
import Model.NowShowingModel;
import Model.ResponseDTO;

public class ViewHolder extends RecyclerView.ViewHolder {
    onItemClicked onItemClicked;
    ImageView imageView;
    TextView title,duration;
    public ViewHolder(@NonNull View itemView, onItemClicked onItemClicked) {
        super(itemView);
        this.onItemClicked=onItemClicked;
        initView(itemView);
    }

    private void initView(View itemView) {
        imageView=itemView.findViewById(R.id.ivImageView);
          title=itemView.findViewById(R.id.tvVideoName);
           duration=itemView.findViewById(R.id.tvDuration);


    }
    public void SetData(NowShowingModel responseDTO){
        Log.d("nalini",responseDTO.getPosterurl().toString());
        if(responseDTO.getPosterurl()!=null) {
            Glide.with(imageView).load(responseDTO.getPosterurl())
                    .placeholder(R.drawable.folderimage)
                    .into(imageView);
            title.setText(responseDTO.getTitle());
            //duration.setText(responseDTO.get.toString());
        }else {
            imageView.setImageResource(R.drawable.folderimage);
        }

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClicked.onItemClicked(responseDTO,getAdapterPosition());

            }
        });

        }
    }

