package Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.playmusic.R;

import java.util.ArrayList;
import java.util.List;

import Interface.ApiCall2;
import Interface.Network2;
import Interface.onItemClicked;
import Model.Model;
import Model.NowShowingModel;
import Model.ResponseModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class downloadFolder extends AppCompatActivity implements onItemClicked {
    RecyclerView recyclerView;
    TextView textView;

    List<NowShowingModel> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_folder);
        initView();
    }

    private void initView() {

        recyclerView = findViewById(R.id.RecycleView11);
        callApi();
    }

    private void callApi() {
        ApiCall2 apiCall2 = Network2.getInstance().create(ApiCall2.class);
        apiCall2.getUser().enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.body() != null) {
                    arrayList = response.body().getNowShowing();
                    setRecycleview();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {

            }
        });
    }

    public void setRecycleview() {
        Adapter adapter = new Adapter(arrayList,this);
        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    public void onItemClicked(NowShowingModel nowShowingDTO, int position) {
        Model model = new Model(nowShowingDTO.getVideoUrl());
//       Bundle bundle=new Bundle();
//       bundle.putString("model",model.getUrl());
//        navController.navigate(R.id.action_fragmentA_to_playVideoFragment,bundle);

        Intent intent = new Intent(getBaseContext(), VideoActivity.class);
        intent.putExtra("model", model);
        startActivity(intent);

    }
}