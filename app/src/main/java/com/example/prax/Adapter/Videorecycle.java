package com.example.prax.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prax.Login;
import com.example.prax.R;
import com.example.prax.Table.Content;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;

public class Videorecycle extends RecyclerView.Adapter<Videorecycle.Myclass> {
    Context context;
    ArrayList<Content> arrayList;
    public Videorecycle(Context context, ArrayList<Content> arrayList){
        this.context=context;
        this.arrayList=arrayList;
    }
    @NonNull
    @Override
    public Myclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.videolayout,parent,false);

        return new Myclass(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Myclass holder, @SuppressLint("RecyclerView") int position) {

        SharedPreferences share = context.getSharedPreferences("data", Context.MODE_PRIVATE);

        holder.name.setText(arrayList.get(position).getConName());
        holder.youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = arrayList.get(position).getConDocument();
                youTubePlayer.cueVideo(videoId, 0);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class Myclass extends RecyclerView.ViewHolder {
        YouTubePlayerView youTubePlayerView;
        TextView name;
        public Myclass(@NonNull View itemView) {
            super(itemView);
            youTubePlayerView =itemView.findViewById(R.id.youtube_player_view);
            name=itemView.findViewById(R.id.vdname);


        }
    }
}
