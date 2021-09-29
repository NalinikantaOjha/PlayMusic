package ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.playmusic.R;

import Model.ParentItem;

public class ParentViewHolder extends RecyclerView.ViewHolder  {
    public TextView ParentItemTitle;
    public RecyclerView ChildRecyclerView;
    public RecyclerView.LayoutManager manager;


    public ParentViewHolder(@NonNull View itemView) {
        super(itemView);
        initView();

    }

    public void initView() {
        ParentItemTitle = itemView.findViewById(R.id.parent_item_title);
        ChildRecyclerView = itemView.findViewById(R.id.child_recyclerview);
    }
    public void setDataParent(ParentItem parentItem){
        ParentItemTitle.setText(parentItem.getParentItemTitle());
        LinearLayoutManager layoutManager = new LinearLayoutManager(ChildRecyclerView.getContext(), LinearLayoutManager.HORIZONTAL, false);
        layoutManager.setInitialPrefetchItemCount(parentItem.getChildItemList().size());
        ChildRecyclerView.setLayoutManager(layoutManager);





    }




}