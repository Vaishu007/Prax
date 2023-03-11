package com.example.prax.Adapter;

import android.content.Context;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prax.R;
import com.example.prax.Table.Faq;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class FaqAdapter extends RecyclerView.Adapter<FaqAdapter.Myclass> {

    Context context;
    ArrayList<Faq> arrayList;

    public  FaqAdapter(Context context, ArrayList<Faq> arrayList){
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public Myclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.faqlayout,parent,false);
        return new Myclass(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Myclass holder, int position) {
        holder.textView.setText(arrayList.get(position).getFqQuestion());
        holder.textView1.setText(arrayList.get(position).getFqAnswer());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class Myclass extends RecyclerView.ViewHolder{
        ImageButton arrow;
        LinearLayout hiddenView;
        CardView cardView;
        TextView textView,textView1;

        public Myclass(@NonNull View itemView) {
            super(itemView);
            arrow = itemView.findViewById(R.id.arrow_buttonh);
            cardView = itemView.findViewById(R.id.base_cardviewh);
            hiddenView = itemView.findViewById(R.id.hidden_viewh);
            textView = itemView.findViewById(R.id.faquestion);
            textView1 = itemView.findViewById(R.id.faqans);

            arrow.setOnClickListener(view -> {
                // If the CardView is already expanded, set its visibility
                // to gone and change the expand less icon to expand more.
                if (hiddenView.getVisibility() == View.VISIBLE) {
                    // The transition of the hiddenView is carried out by the TransitionManager class.
                    // Here we use an object of the AutoTransition Class to create a default transition
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                    hiddenView.setVisibility(View.GONE);
                    arrow.setImageResource(R.drawable.add);
                }

                // If the CardView is not expanded, set its visibility to
                // visible and change the expand more icon to expand less.
                else {
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                    hiddenView.setVisibility(View.VISIBLE);
                    arrow.setImageResource(R.drawable.remove);
                }
            });


        }
    }
}
