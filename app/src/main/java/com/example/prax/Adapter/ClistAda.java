package com.example.prax.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prax.Course_discription;
import com.example.prax.Course_type;
import com.example.prax.Five;
import com.example.prax.Profile_update;
import com.example.prax.R;
import com.example.prax.Table.Coursein;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ClistAda extends RecyclerView.Adapter<ClistAda.Myclass> {
Context context;
ArrayList<Coursein> arrayList;
Course_type course_type;

 public ClistAda(Context context,ArrayList<Coursein> arrayList){
     course_type = (Course_type) context;
     this.arrayList=arrayList;
    this.context=context;

}

    @NonNull
    @Override
    public Myclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.course_list,parent,false);
        return new Myclass(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Myclass holder, int position) {
        holder.textView.setText(arrayList.get(position).getcName());
        Log.e("name",arrayList.get(position).getcName());
        Picasso.get().load(arrayList.get(position).getcImage()).into(holder.img);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class Myclass extends RecyclerView.ViewHolder {
     LinearLayout layout;
        CircleImageView img;
        TextView textView;
        public Myclass(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.clist);
            img=itemView.findViewById(R.id.cimg);
            textView=itemView.findViewById(R.id.cname);
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context,Course_discription.class);
                    intent.putExtra("id",arrayList.get(getAbsoluteAdapterPosition()).getcId());
                    context.startActivity(intent);
                    course_type.finish();
                }
            });
        }
    }
}
