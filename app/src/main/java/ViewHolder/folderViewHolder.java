package ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.playmusic.R;

import Model.ChildItem;

public class folderViewHolder extends RecyclerView.ViewHolder {
   ImageView Image;
  TextView tille;
    public folderViewHolder(@NonNull View itemView) {
        super(itemView);
        initView(itemView);
    }

    private void initView(View view) {
        Image=view.findViewById(R.id.img_your_folder);
        tille=view.findViewById(R.id.child_your_folder_title);


    }
    public void setDataFolder(ChildItem childItem){
      //  Image.setImageResource(R.drawable.folderimage);
        tille.setText(childItem.getChildItemTitle());
    }
}
