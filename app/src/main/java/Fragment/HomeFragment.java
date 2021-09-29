package Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.playmusic.R;

import java.util.ArrayList;
import java.util.List;

import Interface.onChildClicked;
import Model.ChildItem;
import Model.ParentItem;
import ViewHolder.ChildViewHolder;
import ViewHolder.ParentViewHolder;


public class HomeFragment extends Fragment implements onChildClicked {

    RecyclerView.Adapter<ParentViewHolder>adapter;
    RecyclerView.Adapter<ChildViewHolder>adapter2;
    RecyclerView ParentRecyclerViewItem;
    RecyclerView.LayoutManager manager;
    List<ParentItem>itemList=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        manager=new LinearLayoutManager(getContext());
        ParentRecyclerViewItem=view.findViewById(R.id.parent_recyclerview);
        ParentRecyclerViewItem.setLayoutManager(manager);

        adapter=new RecyclerView.Adapter<ParentViewHolder>() {
            @NonNull
            @Override
            public ParentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.parent_item,parent,false);
                return new ParentViewHolder(view);
            }
            @Override
            public void onBindViewHolder(@NonNull ParentViewHolder holder, int position) {
                ParentItem parentItem=itemList.get(position);
                holder.setDataParent(parentItem);

                adapter2=new RecyclerView.Adapter<ChildViewHolder>() {
                    @NonNull
                    @Override
                    public ChildViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.child_item,parent,false);
                        return new ChildViewHolder(view,HomeFragment.this);
                    }
                    @Override
                    public void onBindViewHolder(@NonNull ChildViewHolder holder, int position) {

                        ChildItem childItem = ChildBuildData().get(position);
                        holder.setData(childItem);
                    }

                    @Override
                    public int getItemCount() {
                        return ChildBuildData().size();
                    }
                };
                adapter2.notifyDataSetChanged();
                holder.ChildRecyclerView.setAdapter(adapter2);
            }

            @Override
            public int getItemCount() {
                return itemList.size();
            }
        };
        ParentBuildData();
        setLayout();
        adapter.notifyDataSetChanged();
    }

    private void setLayout() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        ParentRecyclerViewItem.setLayoutManager(layoutManager);
        ParentRecyclerViewItem.setAdapter(adapter);
    }

    private void ParentBuildData() {
        ParentItem item = new ParentItem("Mostly Played", ChildBuildData());itemList.add(item);
        ParentItem item1 = new ParentItem("Your Audios", ChildBuildData());itemList.add(item1);
        ParentItem item2 = new ParentItem("Folders",  ChildBuildData());itemList.add(item2);
        ParentItem item3 = new ParentItem("Recently Added",  ChildBuildData());itemList.add(item3);
        ParentItem item4 = new ParentItem("Album",  ChildBuildData());itemList.add(item4);
        ParentItem item5 = new ParentItem("Artist",  ChildBuildData());itemList.add(item5);

    }

    private List<ChildItem> ChildBuildData() {
        List<ChildItem> ChildItemList = new ArrayList<>();
        ChildItemList.add(new ChildItem("Free Fire"));
        ChildItemList.add(new ChildItem("PUBG"));
        ChildItemList.add(new ChildItem("DR.Driver"));
        ChildItemList.add(new ChildItem("Temple Run"));
        return ChildItemList;
    }

    @Override
    public void onChildClicked(ChildItem childItem, int position) {
        Toast.makeText(getContext(),"pos"+childItem.getChildItemTitle(),Toast.LENGTH_LONG).show();

    }
}