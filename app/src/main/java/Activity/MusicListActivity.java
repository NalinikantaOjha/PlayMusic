package Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import Interface.ApiCall;
import Interface.Network;
import Interface.onChildClicked;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.playmusic.R;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import Model.ChildItem;
import Model.ResponseDTO;
import Model.ResultsDTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MusicListActivity extends AppCompatActivity implements onChildClicked {
RecyclerView recyclerView;
String imageUrl;
    List<ResultsDTO> list=new ArrayList<>();
    Adaptermusic adaptermusic;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list);

        recyclerView=findViewById(R.id.playMusic);
        ApiCall();
       // for (int i = 0; i < 1; i++) {
           // ImageView imageView;
           // imageView=findViewById(R.id.imaF);
            //imageView = findViewById(R.id.nalini);



        }
   // }
    private void ApiCall(){

        ApiCall apiCall= Network.getInstance().create(ApiCall.class);
        apiCall.getPosts("shapeofyou").enqueue(new Callback<ResponseDTO>() {
            @Override
            public void onResponse(Call<ResponseDTO> call, Response<ResponseDTO> response) {
                list.addAll(response.body().getResults());
               setRecyclerView();


            }

            @Override
            public void onFailure(Call<ResponseDTO> call, Throwable t) {

            }
        });


    }

    public void setRecyclerView() {
        adaptermusic=new Adaptermusic(list,this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adaptermusic);

    }

    @Override
    public void onChildClicked(ResultsDTO resultsDTO, int position) {
        startActivity(new Intent(this, PlayMusicActivity.class)
                .putExtra("songs",resultsDTO.getPreviewUrl())
                .putExtra("songName",resultsDTO.getTrackName())
                .putExtra("imageUrl",resultsDTO.getArtworkUrl100())
                .putExtra("pos",position));

    }

    @Override
    public void onFolderClicked(ChildItem childItem) {

    }

    @Override
    public void onParentCLicked() {

    }
}