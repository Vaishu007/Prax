package com.example.prax.Ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.prax.LiveSession;
import com.example.prax.R;
import com.example.prax.VideoSession;
import com.example.prax.ViewAdapter;
import com.google.android.material.tabs.TabLayout;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class Vedio extends Fragment {

    ViewPager vp;
    TabLayout tl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_vedio, container, false);
        vp = v.findViewById(R.id.view);
        tl = v.findViewById(R.id.tblay);


        ViewAdapter va = new ViewAdapter(getChildFragmentManager());
        va.addFragment(new VideoSession(),"Video Session");
        va.addFragment(new LiveSession(),"Live Session");
        vp.setAdapter(va);
        tl.setupWithViewPager(vp);

        return v;
    }
}