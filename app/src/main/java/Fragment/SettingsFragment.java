package Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.playmusic.MainActivity;
import com.example.playmusic.R;
import com.example.playmusic.SleepDialog;


public class SettingsFragment extends Fragment {
    private ImageView share;
    private ImageView Sleep;
    private ImageView Home;
   private NavController navController;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
  /****      NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        NavController navCo = navHostFragment.getNavController();
        //   navController SupportFragmentManager.Navigation.findNavController(view);
         if (navController == null) {
         throw new IllegalStateException("View " + view + " does not have a NavController set");
         }
******/
        share = view.findViewById(R.id.ImgShare);

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.setType("message/rfc822");
                if (intent.resolveActivity(getContext().getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), "Not Found", Toast.LENGTH_LONG).show();
                }
            }
        });
        Sleep(view);


    Home =view.findViewById(R.id.Imagehome);
   Home.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View v){

        GotoHome(view);
    }
    });


}

  private void GotoHome(View view) {
     Intent intent = new Intent(getActivity(), MainActivity.class);
     startActivity(intent);
   }

    private void Sleep(View view) {
        Sleep = view.findViewById(R.id.ImgSleep);
        Sleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
    }

    private void openDialog() {
        SleepDialog sleepDialog = new SleepDialog();
        sleepDialog.show(getActivity().getSupportFragmentManager(), "example Dialpg");
    }
}