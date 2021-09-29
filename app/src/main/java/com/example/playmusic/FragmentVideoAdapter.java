package com.example.playmusic;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragmentVideoAdapter extends FragmentStateAdapter {
    public FragmentVideoAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new VideosInVideoFragment();
            case 1:
                return new FoldersInFVideoragment();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
