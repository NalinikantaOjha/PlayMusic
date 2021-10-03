package Activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.playmusic.R;

import Model.ResultsDTO;
import Interface.onChildClicked;

public class ViewHolderPlay extends RecyclerView.ViewHolder {
    TextView ChildItemTitle;
    ImageView imageView;
    TextView title,duration;
    Interface.onChildClicked onChildClicked;
    public ViewHolderPlay(@NonNull View itemView,onChildClicked onChildClicked) {
        super(itemView);
        this.onChildClicked=onChildClicked;
        initView();
    }

    private void initView() {
        imageView=itemView.findViewById(R.id.ivImageView);
        title=itemView.findViewById(R.id.tvVideoName);
        duration=itemView.findViewById(R.id.tvDuration);
    }


    public void setData5(ResultsDTO resultsDTO) {
       title.setText(resultsDTO.getTrackName());
        Glide.with(imageView).load((resultsDTO).getArtworkUrl100())
                .into(imageView);
        imageView.setOnClickListener(v -> onChildClicked.onChildClicked(resultsDTO,getAdapterPosition()));



    }
}
