package com.example.prax.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prax.Job_discription;
import com.example.prax.R;
import com.example.prax.Table.Company;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class Jobrecycle extends RecyclerView.Adapter<Jobrecycle.MyClass> {

    Context context;
    ArrayList<Company> arrayList;
    public Jobrecycle(Context context, ArrayList<Company> arrayList){
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.joblayout,parent,false);
        return new MyClass(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyClass holder, int position) {
        Picasso.get().load(arrayList.get(position).getComImage()).into(holder.img);
        holder.cname.setText(arrayList.get(position).getComName());
        holder.cposi.setText(arrayList.get(position).getComVacancy());
        holder.cardView.startAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.anim_two));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyClass extends RecyclerView.ViewHolder{
        CircleImageView img;
        TextView cname,cposi;
        Button btn;
        CardView cardView;
        public MyClass(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card);
            img = itemView.findViewById(R.id.comimg);
            cname = itemView.findViewById(R.id.cname);
            cposi = itemView.findViewById(R.id.cposition);
            btn = itemView.findViewById(R.id.jobutton);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, Job_discription.class);
                    intent.putExtra("id",arrayList.get(getAbsoluteAdapterPosition()).getcId());
                    context.startActivity(intent);
                }
            });
        }
    }

}
