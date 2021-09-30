package Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.playmusic.R;

import java.util.List;

import Interface.onItemClicked;
import Model.NowShowingModel;
import ViewHolder.ViewHolder;

public class Adapter extends RecyclerView.Adapter<ViewHolder> {
    onItemClicked onItemClicked;
    List<NowShowingModel> arrayList;
    public Adapter(List<NowShowingModel> arrayList, onItemClicked onItemClicked){
        this.arrayList=arrayList;
        this.onItemClicked=onItemClicked;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);

        return new ViewHolder(view,onItemClicked);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NowShowingModel responseDTO= arrayList.get(position);
        holder.SetData(responseDTO);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
