package ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.playmusic.R;

import Interface.onChildClicked;
import Model.ChildItem;
import Model.ResultsDTO;


public class ChildViewHolder extends RecyclerView.ViewHolder {
    TextView ChildItemTitle;
    ImageView imageView;

    onChildClicked onChildClicked;

    public ChildViewHolder(@NonNull View itemView, onChildClicked onChildClicked) {
        super(itemView);
        initView(itemView);
        this.onChildClicked=onChildClicked;

    }

    public void setData(ResultsDTO resultsDTO) {
        ChildItemTitle.setText(resultsDTO.getTrackName());
        Glide.with(imageView).load((resultsDTO).getPreviewUrl())
                .placeholder(R.drawable.folderimage1)
                .into(imageView);
        imageView.setOnClickListener(v -> onChildClicked.onChildClicked(resultsDTO,getAdapterPosition()));



    }

    private void initView(View itemView) {
        ChildItemTitle = itemView.findViewById(R.id.child_item_title);
        imageView=itemView.findViewById(R.id.img_child_item);
    }



    }

