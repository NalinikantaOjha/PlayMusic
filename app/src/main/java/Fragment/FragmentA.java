package Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.playmusic.R;

import java.util.ArrayList;
import java.util.List;

import Activity.VideoActivity;
import Interface.onItemClicked;
import Interface.ApiCall2;
import Interface.Network2;
import Model.Model;
import Model.NowShowingModel;
import Model.ResponseModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentA extends Fragment implements onItemClicked{

        RecyclerView recyclerView;
    TextView textView;
    List<NowShowingModel> arrayList = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_a, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }
        private void initView(View view) {

        recyclerView = view.findViewById(R.id.RecycleView1);


        callApi();
    }

    private void callApi() {
        ApiCall2 apiCall2 = Network2.getInstance().create(ApiCall2.class);
        apiCall2.getUser().enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.body()!=null){
                    arrayList=response.body().getNowShowing();
                    setRecycleview();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {

            }
        });
    }
    public void setRecycleview(){
        Adapter adapter=new Adapter(arrayList,this);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this.getContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClicked(NowShowingModel nowShowingDTO, int position) {
       Model model=new Model(nowShowingDTO.getVideoUrl());
        Intent intent =new Intent(getContext(), VideoActivity.class);
        intent.putExtra("model",model);
        startActivity(intent);

    }
}