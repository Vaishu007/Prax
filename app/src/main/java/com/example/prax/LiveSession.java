package com.example.prax;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class LiveSession extends Fragment {

    Button btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.fragment_live_session, container, false);

        btn = v.findViewById(R.id.live);

        btn.setOnClickListener(view -> {
           Intent intent = new Intent(getContext(),Jitsi.class);
           startActivity(intent);
        });

        return v;
    }
}