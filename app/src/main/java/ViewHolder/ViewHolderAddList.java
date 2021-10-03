package ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.playmusic.R;

import Model.AddList;

public class ViewHolderAddList extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView textView;
    public ViewHolderAddList(@NonNull View itemView) {
        super(itemView);
        initView(itemView);

    }

    private void initView(View view) {
        imageView=view.findViewById(R.id.ivImageView);
        textView=view.findViewById(R.id.tvVideoName);

    }
    public void SetData(AddList addList){
        Glide.with(imageView).load(addList.getUrl()).into(imageView);
        textView.setText(addList.getSongName());

    }
}
