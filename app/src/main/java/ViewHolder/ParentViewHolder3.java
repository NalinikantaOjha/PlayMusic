package ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.playmusic.R;

import Model.ParentItem;

public class ParentViewHolder3 extends RecyclerView.ViewHolder {
    public TextView ParentItemTitle;
    public RecyclerView ChildRecyclerView;
    public RecyclerView.LayoutManager manager;


    public ParentViewHolder3(@NonNull View itemView) {
        super(itemView);
        initView();

    }

    public void initView() {
        ParentItemTitle = itemView.findViewById(R.id.parent_item_title);
        ChildRecyclerView = itemView.findViewById(R.id.child_recyclerview);
    }
    public void setDataParent3(ParentItem parentItem){
        ParentItemTitle.setText(parentItem.getParentItemTitle());
        LinearLayoutManager layoutManager = new LinearLayoutManager(ChildRecyclerView.getContext(), LinearLayoutManager.HORIZONTAL, false);
        layoutManager.setInitialPrefetchItemCount(parentItem.getChildItems().size());
        ChildRecyclerView.setLayoutManager(layoutManager);





    }
}
