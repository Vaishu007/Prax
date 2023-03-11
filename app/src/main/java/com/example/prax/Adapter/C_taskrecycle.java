package com.example.prax.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prax.R;
import com.example.prax.Table.Content;
import com.example.prax.ViewPdf;


import java.util.ArrayList;

public class C_taskrecycle extends RecyclerView.Adapter<C_taskrecycle.Myclass> {
    Context context;
    ArrayList<Content> arrayList;
    public C_taskrecycle(Context context, ArrayList<Content> arrayList){
        this.context=context;
        this.arrayList=arrayList;
    }
    @NonNull
    @Override
    public Myclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.ctasklayout,parent,false);
        return new Myclass(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Myclass holder, int position) {
        holder.t1.setText(arrayList.get(position).getConChno());
        holder.t2.setText(arrayList.get(position).getConChname());
        holder.t3.setText(arrayList.get(position).getConName());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class Myclass extends RecyclerView.ViewHolder {
        TextView t1,t2,t3;
        ImageButton b1,b2;
        public Myclass(@NonNull View itemView) {
            super(itemView);
            t1=itemView.findViewById(R.id.task_chno);
            t2=itemView.findViewById(R.id.task_chname);
            t3=itemView.findViewById(R.id.task_name);
            b1=itemView.findViewById(R.id.task_view);

            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, ViewPdf.class);
                    intent.putExtra("url",arrayList.get(getAdapterPosition()).getConDocument());
                    context.startActivity(intent);
                }
            });
        }
    }
}
