package com.example.prax.Ui;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.prax.R;

public class Home extends Fragment {


    ImageView iv;
    AnimationDrawable manimation=new AnimationDrawable();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_home, container, false);
        iv=v.findViewById(R.id.imganiim);

        BitmapDrawable frame1= (BitmapDrawable) getResources().getDrawable(R.drawable.praxlogo);
        BitmapDrawable frame2= (BitmapDrawable) getResources().getDrawable(R.drawable.second);
        BitmapDrawable frame3= (BitmapDrawable) getResources().getDrawable(R.drawable.third);
        manimation.addFrame(frame1,1500);
        manimation.addFrame(frame2,1500);
        manimation.addFrame(frame3,1500);
        iv.setBackgroundDrawable(manimation);
        manimation.setOneShot(false);
        manimation.start();
        return v;
    }
}