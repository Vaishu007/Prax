package com.example.prax.Ui;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.prax.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class Home extends Fragment {


    ImageView iv;
    AnimationDrawable manimation=new AnimationDrawable();
    YouTubePlayerView youTubePlayerView;
    CardView cardView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_home, container, false);
        iv=v.findViewById(R.id.homeImg);
        youTubePlayerView = v.findViewById(R.id.youtube_player_view);
        cardView = v.findViewById(R.id.cardView);


        BitmapDrawable frame1= (BitmapDrawable) getResources().getDrawable(R.drawable.praxlogo);
        BitmapDrawable frame2= (BitmapDrawable) getResources().getDrawable(R.drawable.second);
        BitmapDrawable frame3= (BitmapDrawable) getResources().getDrawable(R.drawable.third);
        manimation.addFrame(frame1,1500);
        manimation.addFrame(frame2,1500);
        manimation.addFrame(frame3,1500);
        iv.setBackgroundDrawable(manimation);
        manimation.setOneShot(false);
        manimation.start();

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


//        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
//            @Override
//            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
//                youTubePlayer.loadVideo("phWxA89Dy94", 0);
//            }
//        });
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                youTubePlayer.cueVideo("phWxA89Dy94", 0);
            }
        });
        return v;
    }
}