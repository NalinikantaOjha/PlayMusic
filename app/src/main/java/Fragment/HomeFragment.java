package Fragment;

import static android.content.Context.BIND_AUTO_CREATE;
import android.content.Intent;
import android.util.Log;

import com.example.playmusic.MusicService;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.playmusic.MusicService;
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
import ViewHolder.YourAudiosViewHolder;
import ViewHolder.folderViewHolder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment implements onChildClicked {
    RecyclerView.Adapter<YourAudiosViewHolder>adapterAudios;

    RecyclerView.Adapter<ParentViewHolder>adapter;
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
        play=view.findViewById(R.id.btnPlay);
        pause=view.findViewById(R.id.btnPause);
        ApiCall();


        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                musicService.onPlay();
            }
        });
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                musicService.onPause();
            }
        });

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

                        ResultsDTO resultsDTO = list.get(position);
                        holder.setData(resultsDTO);
                    }

                    @Override
                    public int getItemCount() {
                        return list.size();
                    }
                };

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
              //  adapter2.notifyDataSetChanged();
                holder.ChildRecyclerView.setAdapter(adapterAudios);
             //   holder.ChildRecyclerView.setAdapter(adapter2);
//                adapter3=new RecyclerView.Adapter<folderViewHolder>() {
//                    @NonNull
//                    @Override
//                    public folderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                        View view1= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_folder,parent,false);
//                        return new folderViewHolder(view1);
//                    }
//
//                    @Override
//                    public void onBindViewHolder(@NonNull folderViewHolder holder, int position) {
//                        ChildItem childItem=ChildBuildData().get(position);
//                        holder.setDataFolder(childItem);
//
//                    }
//
//                    @Override
//                    public int getItemCount() {
//                        return ChildBuildData().size();
//                    }
//                };
             //  adapter3.notifyDataSetChanged();
              // holder.ChildRecyclerView.setAdapter(adapter3);


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
        ParentItem item = new ParentItem("Mostly Played",list2,null,null);itemList.add(item);
     ParentItem item1 = new ParentItem("Your Audios",list2, null,null);itemList.add(item1);
        ParentItem item2 = new ParentItem("Folders",null,list,  null);itemList.add(item2);
//       ParentItem item3 = new ParentItem("Recently Added",  list);itemList.add(item3);
//       ParentItem item4 = new ParentItem("Album",  list);itemList.add(item4);
//       ParentItem item5 = new ParentItem("Artist",  list);itemList.add(item5);

    }
    private void ApiCall(){

        ApiCall apiCall= Network.getInstance().create(ApiCall.class);
     apiCall.getPosts("shapeofyou").enqueue(new Callback<ResponseDTO>() {
         @Override
         public void onResponse(Call<ResponseDTO> call, Response<ResponseDTO> response) {
          //  list.addAll(response.body().getResults());
             //adapter2.notifyDataSetChanged();

             list2=response.body().getResults();
            adapterAudios.notifyDataSetChanged();
             adapter.notifyDataSetChanged();
             //adapter2.notifyDataSetChanged();


            // adapter.notifyDataSetChanged();

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
        Intent intent=new Intent(getContext(),MusicService.class);
        //intent.putExtra("nalini",resultsDTO.get)
StartService();
    }
    ImageView imageView;
    Button play,start,pause,Stop;
    int id;
    TextView textView;
    private MusicService musicService;
    private ServiceConnection serviceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicService.ServiceBinder serviceBinder=(MusicService.ServiceBinder)service;
            musicService=serviceBinder.getMusicService();

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private void StartService(){
        Intent intent =new Intent(getContext(), MusicService.class);
        Log.d("nalini",id+"");
        intent.putExtra("nalini",id);
      // bindService(intent,serviceConnection,BIND_AUTO_CREATE);
    }

    }
