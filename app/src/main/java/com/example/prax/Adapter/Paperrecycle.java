package com.example.prax.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prax.R;
import com.example.prax.Table.Material;
import com.example.prax.ViewPdf;

import java.util.ArrayList;

public class Paperrecycle extends RecyclerView.Adapter<Paperrecycle.Myclass>{
    Context context;
    ArrayList<Material> arrayList;
    public Paperrecycle(Context context, ArrayList<Material> arrayList){
        this.context=context;
        this.arrayList=arrayList;
    }
    @NonNull
    @Override
    public Myclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.paperlayout,parent,false);
        return new Myclass(v);    }

    @Override
    public void onBindViewHolder(@NonNull Myclass holder, int position) {
        holder.n.setText(arrayList.get(position).getmName());
        holder.s.setText(arrayList.get(position).getmSubject());
        holder.c.setText(arrayList.get(position).getmSubcode());
        holder.cv.startAnimation(AnimationUtils.loadAnimation(context,R.anim.anim_four));

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class Myclass extends RecyclerView.ViewHolder{
        TextView n,s,c;
        CardView cv;
        ImageButton d;
        public Myclass(@NonNull View itemView) {
            super(itemView);
            n=itemView.findViewById(R.id.p_name);
            s=itemView.findViewById(R.id.p_sub);
            c=itemView.findViewById(R.id.p_code);
            d=itemView.findViewById(R.id.p_download);
            cv=itemView.findViewById(R.id.pcv);
            cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, ViewPdf.class);
                    intent.putExtra("url",arrayList.get(getAdapterPosition()).getmDocument());
                    context.startActivity(intent);
                }
            });

        }
    }
}
