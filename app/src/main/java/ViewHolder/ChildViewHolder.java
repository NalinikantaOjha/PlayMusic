package ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.playmusic.R;

import Interface.onChildClicked;
import Model.ChildItem;


public class ChildViewHolder extends RecyclerView.ViewHolder {
    TextView ChildItemTitle;
    ImageView imageView;

   public Interface.onChildClicked onChildClicked;

    public ChildViewHolder(@NonNull View itemView, onChildClicked onChildClicked) {
        super(itemView);
        initView(itemView);
        this.onChildClicked=onChildClicked;

    }

    public void setData(ChildItem childItem) {
        ChildItemTitle.setText(childItem.getChildItemTitle());
        imageView.setOnClickListener(v -> onChildClicked.onChildClicked(childItem,getAdapterPosition()));



    }

    private void initView(View itemView) {
        ChildItemTitle = itemView.findViewById(R.id.child_item_title);
        imageView=itemView.findViewById(R.id.img_child_item);
    }



    }

