package ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.playmusic.R;

import Interface.onChildClicked;
import Model.ResultsDTO;

public class YourAudiosViewHolder extends RecyclerView.ViewHolder {
    TextView ChildItemTitle;
    ImageView imageView;

    Interface.onChildClicked onChildClicked;
    public YourAudiosViewHolder(@NonNull View itemView,onChildClicked onChildClicked) {

        super(itemView);
        this.onChildClicked=onChildClicked;
        initView(itemView);
    }
    private void initView(View itemView) {
        ChildItemTitle = itemView.findViewById(R.id.child_your_audio_title5);
        imageView=itemView.findViewById(R.id.img_your_audios5);
    }
    public void setData2(ResultsDTO resultsDTO) {
        ChildItemTitle.setText(resultsDTO.getTrackName());
        Glide.with(imageView).load((resultsDTO).getArtworkUrl100())
                               .into(imageView);
        imageView.setOnClickListener(v -> onChildClicked.onChildClicked(resultsDTO,getAdapterPosition()));



    }
}
