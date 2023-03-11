package com.example.prax;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.prax.Adapter.Videorecycle;
import com.example.prax.Json.Contentjson;
import com.example.prax.Table.Content;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoSession extends Fragment {
    RecyclerView rv;
    String type="Video";
    ArrayList<Content> arrayList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.fragment_video_session, container, false);
        rv=v.findViewById(R.id.videorv);
        arrayList=new ArrayList<>();
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        Apiinterface apiinterface= Apiclient.praxware().create(Apiinterface.class);
        Call<Contentjson> call=apiinterface.contentshow(type);
        call.enqueue(new Callback<Contentjson>() {
            @Override
            public void onResponse(Call<Contentjson> call, Response<Contentjson> response) {
                arrayList= (ArrayList<Content>) response.body().getContent();
                Videorecycle videorecycle=new Videorecycle(getContext(),arrayList);
                rv.setAdapter(videorecycle);
            }

            @Override
            public void onFailure(Call<Contentjson> call, Throwable t) {

            }
        });

        return v;
    }
}