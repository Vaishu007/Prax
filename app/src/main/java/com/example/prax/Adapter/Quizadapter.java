package com.example.prax.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prax.C_quize;
import com.example.prax.C_sql;
import com.example.prax.R;
import com.example.prax.Table.Exam;
import com.example.prax.Table.Question;
import com.example.prax.ViewPdf;

import java.util.ArrayList;
import java.util.List;

public class Quizadapter extends RecyclerView.Adapter<Quizadapter.Myclass> {
    int score;

    String que, op1, op2, op3, op4, ans;
    String rightAnswer;
    String Answer;
    int counter=0;

    ArrayList<Exam> arrayList;
    Context context;

    public Quizadapter(Context context, ArrayList<Exam> arrayList) {
        this.arrayList = arrayList;
        this.context = context;

    }

    @NonNull
    @Override
    public Myclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.quizlayout, parent, false);
        return new Myclass(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Myclass holder, int position) {
        que = arrayList.get(counter).geteQuestion();
        op1 = arrayList.get(counter).geteOpt1();
        op2 = arrayList.get(counter).geteOpt2();
        op3 = arrayList.get(counter).geteOpt3();
        op4 = arrayList.get(counter).geteOpt4();
        ans = arrayList.get(counter).geteAns();
        holder.qus.setText(que);
        holder.o1.setText(op1);
        holder.o2.setText(op2);
        holder.o3.setText(op3);
        holder.o4.setText(op4);
        holder.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               counter++;
                int c=arrayList.size();
               if(c!=counter){
                Toast.makeText(context, arrayList.get(counter).geteOpt4(), Toast.LENGTH_SHORT).show();
                Log.e("array1",arrayList.get(counter).geteOpt4());
                holder.qus.setText(arrayList.get(counter).geteQuestion());
                holder.o1.setText( arrayList.get(counter).geteOpt1());
                holder.o2.setText( arrayList.get(counter).geteOpt2());
                holder.o3.setText( arrayList.get(counter).geteOpt3());
                holder.o4.setText(arrayList.get(counter).geteOpt4());
                rightAnswer=arrayList.get(counter).geteAns();
                int op = holder.radioGroup.getCheckedRadioButtonId();

        switch (op) {
            case R.id.opta:
                Answer = "A";
                break;

            case R.id.optb:
                Answer = "B";
                break;

            case R.id.optc:
                Answer = "C";
                break;

            case R.id.optd:
                Answer = "D";
                break;

            default:
                return ;

        }

        holder.radioGroup.clearCheck();

                if (Answer.equals(rightAnswer)) {
                    score += 1;
                    Toast.makeText(context, "right"+score, Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(context, "wrong"+score, Toast.LENGTH_SHORT).show();

                }

            }else{
                Intent intent =new Intent(context,C_sql.class);
                context.startActivity(intent);
            }}

        });



    }

    @Override
    public int getItemCount() {

        return arrayList.size();
    }
//



    class Myclass extends RecyclerView.ViewHolder {
        TextView qus;
        RadioGroup radioGroup;
        RadioButton o1, o2, o3, o4;
        Button submit;

        public Myclass(@NonNull View itemView) {
            super(itemView);
            qus = itemView.findViewById(R.id.qus);
            o1 = itemView.findViewById(R.id.opta);
            o2 = itemView.findViewById(R.id.optb);
            o3 = itemView.findViewById(R.id.optc);
            o4 = itemView.findViewById(R.id.optd);
            submit = itemView.findViewById(R.id.submit);
            radioGroup = itemView.findViewById(R.id.rg1);
//            submit.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    loadAnswer(view);
//                    loadquestion(que,op1,op2,op3,op4,ans);
//                }
//            });
//        }

        }


    }


//
}