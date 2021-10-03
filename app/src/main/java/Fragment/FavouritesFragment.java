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
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.playmusic.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import Adapter.AdapterAddList;
import Model.AddList;


public class FavouritesFragment extends Fragment {
ImageView textView;
RecyclerView recyclerView;
AdapterAddList adapterAddList;
    List<AddList>addLists=new ArrayList<>();
    TextView textView1;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
     DatabaseReference myRef = database.getReference("parent");
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourites, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       textView=view.findViewById(R.id.nalini);
       textView1=view.findViewById(R.id.kantatext);
        recyclerView=view.findViewById(R.id.addRecycleView);
        for (int i = 0; i < 10; i++) {

            DatabaseReference myRef = database.getReference("parent"+i+"");

            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    GenericTypeIndicator<AddList> genericTypeIndicator = new GenericTypeIndicator<AddList>() {


                    };
                    AddList student2 = snapshot.getValue(genericTypeIndicator);
                    if (student2 != null) {
                        addLists.add(student2);
                       // Glide.with(textView).load(student2.getUrl()).into(textView);
                        // Student student=snapshot.getValue(genericTypeIndicator);
                        Glide.with(textView).load(student2.getUrl()).into(textView);
                        textView1.setText(student2.getSongName());

                        // Toast.makeText(getContext(),student2.getSongName(),Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
      }

        SetRecycleView();
    }
    public void SetRecycleView(){
        adapterAddList=new AdapterAddList(addLists);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        recyclerView.setAdapter(adapterAddList);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
}