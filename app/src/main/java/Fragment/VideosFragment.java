package Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.playmusic.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

import Interface.ApiCall2;
import Interface.Network2;
import Model.NowShowingModel;
import Model.ResponseModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class VideosFragment extends Fragment {
    TabLayout tabLayout;
    ViewPager2 viewPager2;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_videos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
      

initView(view);
setAdapter();
        
    }
    private void initView(View view) {
        tabLayout=view.findViewById(R.id.tabLayout);
        viewPager2=view.findViewById(R.id.viewPager);
    }
    private void setAdapter() {
        PagerAdapte pagerAdapte=new PagerAdapte(getFragmentManager(),getLifecycle());
        viewPager2.setAdapter(pagerAdapte);
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if (position==0) {
                    tab.setText("Videos");
                }else if (position==1){
                    tab.setText("Folders");
                }else {
                    tab.setText("Turist Places");
                }

            }
        }).attach();

    }

}