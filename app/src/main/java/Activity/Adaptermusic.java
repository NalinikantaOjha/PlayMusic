package Activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.playmusic.R;

import java.util.ArrayList;
import java.util.List;

import Fragment.HomeFragment;
import  Interface.onChildClicked;

import Model.ResultsDTO;
import ViewHolder.YourAudiosViewHolder;

public class Adaptermusic extends RecyclerView.Adapter<YourAudiosViewHolder> {
    List<ResultsDTO> list=new ArrayList();
    onChildClicked onChildClicked;

    public Adaptermusic(List list, Interface.onChildClicked onChildClicked) {
        this.list = list;
        this.onChildClicked = onChildClicked;
    }

    @NonNull
    @Override
    public YourAudiosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view1= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_your_audios,parent,false);
        return new YourAudiosViewHolder(view1, onChildClicked);
    }

    @Override
    public void onBindViewHolder(@NonNull YourAudiosViewHolder holder, int position) {
        ResultsDTO resultsDTO=list.get(position);
        holder.setData2(resultsDTO);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
