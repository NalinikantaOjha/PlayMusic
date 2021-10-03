package Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.playmusic.R;

import java.util.ArrayList;
import java.util.List;

import Model.AddList;
import ViewHolder.ViewHolder;
import ViewHolder.ViewHolderAddList;

public class AdapterAddList extends RecyclerView.Adapter<ViewHolderAddList> {
    List<AddList>addLists=new ArrayList<>();

    public AdapterAddList(List<AddList> addLists) {
        this.addLists = addLists;
    }

    @NonNull
    @Override
    public ViewHolderAddList onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);

        return new ViewHolderAddList(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderAddList holder, int position) {
        AddList addList=addLists.get(position);
holder.SetData(addList);
    }

    @Override
    public int getItemCount() {
        return addLists.size();
    }
}
