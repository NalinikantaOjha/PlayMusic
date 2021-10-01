package Fragment;

import static android.content.Context.BIND_AUTO_CREATE;
import android.content.Intent;
import android.util.Log;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.playmusic.R;

import java.util.ArrayList;
import java.util.List;

import Interface.ApiCall;
import Interface.Network;
import Interface.onChildClicked;
import Model.ChildItem;
import Model.ParentItem;
import Model.ResponseDTO;
import Model.ResultsDTO;
import ViewHolder.ChildViewHolder;
import ViewHolder.ParentViewHolder;
import ViewHolder.ParentViewHolder2;
import ViewHolder.ParentViewHolder3;
import ViewHolder.ParentViewHolder4;
import ViewHolder.ParentViewHolder5;
import ViewHolder.YourAudiosViewHolder;
import ViewHolder.folderViewHolder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment implements onChildClicked {
    RecyclerView.Adapter<YourAudiosViewHolder>adapterAudios;

    RecyclerView.Adapter<RecyclerView.ViewHolder>adapter;
    RecyclerView.Adapter<ChildViewHolder>adapter2;
    RecyclerView.Adapter<folderViewHolder>adapter3;

    RecyclerView ParentRecyclerViewItem;

   RecyclerView.LayoutManager manager;
    List<ParentItem>itemList=new ArrayList<>();
    List<ResultsDTO>list=new ArrayList<>();
    List<ResultsDTO>list2=new ArrayList<>();
  //  List<ChildItem>childItems=new ArrayList<>();

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

        ApiCall();

        adapter=new RecyclerView.Adapter<RecyclerView.ViewHolder>() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
               switch (viewType){
                   case ParentItem.TEXT_TYPE:
                       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.parent_item,parent,false);
                       return new ParentViewHolder(view);
                       case ParentItem.TEXT_TYPE_TWO:
                           View view1= LayoutInflater.from(parent.getContext()).inflate(R.layout.parent_item,parent,false);
                           return new ParentViewHolder2(view1);
                           case ParentItem.TEXT_TYPE_THREE:
                               View view3= LayoutInflater.from(parent.getContext()).inflate(R.layout.parent_item,parent,false);
                               return new ParentViewHolder3(view3);
                               case ParentItem.TEXT_TYPE_FOUR:
                                   View view4= LayoutInflater.from(parent.getContext()).inflate(R.layout.parent_item_layout2,parent,false);
                                   return new ParentViewHolder4(view4,HomeFragment.this);
                                   case ParentItem.TEXT_TYPE_FIVE:
                                       View view5= LayoutInflater.from(parent.getContext()).inflate(R.layout.parent_item_layout2,parent,false);
                                       return new ParentViewHolder5(view5,HomeFragment.this);


               }
                return null;
            }
            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
               int viewType=itemList.get(position).getType();
                ParentItem parentItem=itemList.get(position);
               switch (viewType){
                   case ParentItem.TEXT_TYPE:
                       if (holder instanceof ParentViewHolder){
                           ((ParentViewHolder)holder).setDataParent(parentItem);
                           adapter2=new RecyclerView.Adapter<ChildViewHolder>() {
                               @NonNull
                               @Override
                               public ChildViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                                   View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.child_item,parent,false);
                                   return new ChildViewHolder(view,HomeFragment.this);
                               }
                               @Override
                               public void onBindViewHolder(@NonNull ChildViewHolder holder, int position) {

                                   ResultsDTO resultsDTO = list.get(position);
                                   holder.setData(resultsDTO);
                               }

                               @Override
                               public int getItemCount() {
                                   return list.size();
                               }
                           };

                            adapter2.notifyDataSetChanged();
                             ((ParentViewHolder) holder).ChildRecyclerView.setAdapter(adapter2);

                       }break;
                       case ParentItem.TEXT_TYPE_TWO:
                           if (holder instanceof ParentViewHolder2){
                               ((ParentViewHolder2)holder).setDataParent2(parentItem);
                               adapterAudios=new RecyclerView.Adapter<YourAudiosViewHolder>() {
                    @NonNull
                    @Override
                    public YourAudiosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view1= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_your_audios,parent,false);
                        return new YourAudiosViewHolder(view1,HomeFragment.this);
                    }

                    @Override
                    public void onBindViewHolder(@NonNull YourAudiosViewHolder holder, int position) {
                        ResultsDTO resultsDTO=list2.get(position);
                      holder.setData2(resultsDTO);
                    }

                    @Override
                    public int getItemCount() {
                        return list2.size();
                    }
                };
                adapterAudios.notifyDataSetChanged();
                ((ParentViewHolder2) holder).ChildRecyclerView.setAdapter(adapterAudios);
                           }
                           break;
                           case ParentItem.TEXT_TYPE_THREE:
                               if (holder instanceof ParentViewHolder3){
                                   ((ParentViewHolder3)holder).setDataParent3(parentItem);
                                   adapter3=new RecyclerView.Adapter<folderViewHolder>() {
                    @NonNull
                    @Override
                    public folderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view1= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_folder,parent,false);
                        return new folderViewHolder(view1,HomeFragment.this);
                    }

                    @Override
                    public void onBindViewHolder(@NonNull folderViewHolder holder, int position) {
                        ChildItem childItem=ChildBuildData().get(position);
                        holder.setDataFolder(childItem);

                    }

                    @Override
                    public int getItemCount() {
                        return ChildBuildData().size();
                    }
                };
                   adapter3.notifyDataSetChanged();
                   ((ParentViewHolder3) holder).ChildRecyclerView.setAdapter(adapter3);

                                   }
                               break;
               case ParentItem.TEXT_TYPE_FOUR:
                   if (holder instanceof ParentViewHolder4){
                       ((ParentViewHolder4)holder).setDataParent4(parentItem);
                   }break;
                   case ParentItem.TEXT_TYPE_FIVE:
                       if (holder instanceof ParentViewHolder5){
                           ((ParentViewHolder5)holder).setDataParent5(parentItem);
                       }
               }


//
//

            }

            @Override
            public int getItemCount() {
                return itemList.size();
            }
            @Override
            public int getItemViewType(int position) {
                int itemType = itemList.get(position).getType();
                switch (itemType) {
                    case ParentItem.TEXT_TYPE:
                        return ParentItem.TEXT_TYPE;
                    case ParentItem.TEXT_TYPE_TWO:
                        return ParentItem.TEXT_TYPE_TWO;
                    case ParentItem.TEXT_TYPE_THREE:
                        return ParentItem.TEXT_TYPE_THREE;
                    case ParentItem.TEXT_TYPE_FOUR:
                        return ParentItem.TEXT_TYPE_FOUR;
                        case ParentItem.TEXT_TYPE_FIVE:
                            return ParentItem.TEXT_TYPE_FIVE;

                }
                return -1;
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
        ParentItem item = new ParentItem(ParentItem.TEXT_TYPE,"Mostly Played",list2,null,null);itemList.add(item);
     ParentItem item1 = new ParentItem(ParentItem.TEXT_TYPE_TWO,"Your Audios",null, list,null);itemList.add(item1);
     ParentItem item2 = new ParentItem(ParentItem.TEXT_TYPE_THREE,"Folders",null,null,  ChildBuildData());itemList.add(item2);
      ParentItem item3 = new ParentItem(ParentItem.TEXT_TYPE_FOUR,"Recently Added",  null,null,null);itemList.add(item3);
      ParentItem item4 = new ParentItem(ParentItem.TEXT_TYPE_FIVE,"Album",  null,null,null);itemList.add(item4);
//       ParentItem item5 = new ParentItem("Artist",  list);itemList.add(item5);

    }
    private void ApiCall(){

        ApiCall apiCall= Network.getInstance().create(ApiCall.class);
     apiCall.getPosts("shapeofyou").enqueue(new Callback<ResponseDTO>() {
         @Override
         public void onResponse(Call<ResponseDTO> call, Response<ResponseDTO> response) {
            list.addAll(response.body().getResults());
             adapter2.notifyDataSetChanged();

             list2=response.body().getResults();
            adapterAudios.notifyDataSetChanged();
             adapter.notifyDataSetChanged();

         }

         @Override
         public void onFailure(Call<ResponseDTO> call, Throwable t) {

         }
     });


    }


    private List<ChildItem> ChildBuildData() {
        List<ChildItem> ChildItemList = new ArrayList<>();
        ChildItemList.add(new ChildItem("My Music"));
        ChildItemList.add(new ChildItem("My Recording"));
        ChildItemList.add(new ChildItem("Audio"));

        return ChildItemList;
    }


    @Override
    public void onChildClicked(ResultsDTO resultsDTO, int position) {
        Toast.makeText(getContext(),resultsDTO.getTrackName().toString(),Toast.LENGTH_LONG).show();


    }

    @Override
    public void onFolderClicked(ChildItem childItem) {
        Toast.makeText(getContext(),childItem.getChildItemTitle(),Toast.LENGTH_LONG).show();

    }

    @Override
    public void onParentCLicked() {
        Toast.makeText(getContext(),"onParent Clicked",Toast.LENGTH_LONG).show();

    }
}
