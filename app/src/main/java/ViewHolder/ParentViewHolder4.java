package ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.playmusic.R;

import java.util.concurrent.Callable;
import Interface.onChildClicked;
import Model.ParentItem;

public class ParentViewHolder4 extends RecyclerView.ViewHolder {
    public TextView ParentItemTitle;
    public RecyclerView ChildRecyclerView;
    ImageView imageView;
    public RecyclerView.LayoutManager manager;
onChildClicked onChildClicked;

    public ParentViewHolder4(@NonNull View itemView,onChildClicked onChildClicked) {
        super(itemView);
       this.onChildClicked=onChildClicked;
        initView();

    }

    public void initView() {
        ParentItemTitle = itemView.findViewById(R.id.parent_item_title2);
imageView=itemView.findViewById(R.id.img_your_audios7);
     //   ChildRecyclerView=itemView.findViewById(R.id.child_recyclerview);
    }
    public void setDataParent4(ParentItem parentItem){
        ParentItemTitle.setText(parentItem.getParentItemTitle());
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onChildClicked.onParentCLicked();
            }
        });
//        LinearLayoutManager layoutManager = new LinearLayoutManager(ChildRecyclerView.getContext(), LinearLayoutManager.HORIZONTAL, false);
//        layoutManager.setInitialPrefetchItemCount(parentItem.getChildItems().size());
//        ChildRecyclerView.setLayoutManager(layoutManager);





    }
}
