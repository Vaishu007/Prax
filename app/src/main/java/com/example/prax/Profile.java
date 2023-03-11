package com.example.prax;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.prax.Json.Courseins;
import com.example.prax.Json.Regisjson;
import com.example.prax.Table.Coursein;
import com.example.prax.Table.Praxregi;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile extends AppCompatActivity {
    ImageButton arrow,arrow2,arrow3;
    LinearLayout hiddenView,hiddenView2,hiddenView3;
    CardView cardView,cardView2,cardView3;
    Button btn;
    CircleImageView iv;
    TextView name,email,contact,add,clg,clgadd,branch,sem,course,dob,gender,pass,sdate;
    ArrayList<Praxregi> arrayList;
    ArrayList<Coursein> arrayList1;
    String pid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        arrow = findViewById(R.id.arrow_button);
        arrow2=findViewById(R.id.arrow_button2);
        arrow3=findViewById(R.id.arrow_button3);

        cardView = findViewById(R.id.base_cardview);
        cardView2=findViewById(R.id.base_cardview2);
        cardView3=findViewById(R.id.base_cardview3);

        hiddenView = findViewById(R.id.hidden_view);
        hiddenView2=findViewById(R.id.hidden_view2);
        hiddenView3=findViewById(R.id.hidden_view3);

        btn=findViewById(R.id.button2);
        iv=findViewById(R.id.image);

        name=findViewById(R.id.uname);
        contact=findViewById(R.id.ucon);
        email=findViewById(R.id.umail);
        add=findViewById(R.id.uadd);
        dob=findViewById(R.id.udob);
        gender=findViewById(R.id.ugen);
        clg=findViewById(R.id.uclg);
        clgadd=findViewById(R.id.uloc);
        branch = findViewById(R.id.ubranch);
        sem = findViewById(R.id.usem);
        course = findViewById(R.id.ucourse);
        pass=findViewById(R.id.upass);
        sdate=findViewById(R.id.startd);


        arrayList=new ArrayList<>();
        arrayList1=new ArrayList<>();

        btn.setOnClickListener(view -> {
            Intent intent = new Intent(Profile.this,Profile_update.class);
            intent.putExtra("id",pid);
            startActivity(intent);
        });

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

        arrow2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (hiddenView2.getVisibility() == View.VISIBLE) {
                    TransitionManager.beginDelayedTransition(cardView2, new AutoTransition());
                    hiddenView2.setVisibility(
                            View.GONE);
                    arrow2.setImageResource(R.drawable.add);
                } else{
                    TransitionManager.beginDelayedTransition(cardView2,new AutoTransition());
                    hiddenView2.setVisibility(View.VISIBLE);
                    arrow2.setImageResource(R.drawable.remove);
                }
            }
        });
        arrow3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (hiddenView3.getVisibility() == View.VISIBLE) {
                    TransitionManager.beginDelayedTransition(cardView3, new AutoTransition());
                    hiddenView3.setVisibility(View.GONE);
                    arrow3.setImageResource(R.drawable.add);
                } else{
                    TransitionManager.beginDelayedTransition(cardView3,new AutoTransition());
                    hiddenView3.setVisibility(View.VISIBLE);
                    arrow3.setImageResource(R.drawable.remove);
                }
            }
        });
        SharedPreferences share = getSharedPreferences("data", Context.MODE_PRIVATE);
       int id= share.getInt("id",0);

        Apiinterface apiinterface= Apiclient.praxware().create(Apiinterface.class);
        Call<Regisjson> call= apiinterface.profileshow(String.valueOf(id));
        call.enqueue(new Callback<Regisjson>() {
            @Override
            public void onResponse(Call<Regisjson> call, Response<Regisjson> response) {
                arrayList= (ArrayList<Praxregi>) response.body().getPraxregi();
                name.setText(arrayList.get(0).getuName());
                email.setText(arrayList.get(0).getuEmail());
                contact.setText(arrayList.get(0).getuContact());
                add.setText(arrayList.get(0).getuAddress());
                gender.setText(arrayList.get(0).getuGender());
                dob.setText(arrayList.get(0).getuDob());
                Picasso.get().load(arrayList.get(0).getuImage()).into(iv);

                clg.setText(arrayList.get(0).getuCollege());
                branch.setText(arrayList.get(0).getuBranch());
                sem.setText(arrayList.get(0).getuSem());
                pass.setText(arrayList.get(0).getuPassword());
                clgadd.setText(arrayList.get(0).getuCaddress());
                sdate.setText(arrayList.get(0).getuCdate());

                String cid=arrayList.get(0).getcId();
                pid=arrayList.get(0).getuId();

                Cours(cid);
            }

            @Override
            public void onFailure(Call<Regisjson> call, Throwable t) {

            }
        });




    }
    public void Cours(String id){
        Apiinterface apiinterface=Apiclient.praxware().create(Apiinterface.class);
        Call<Courseins> call=apiinterface.CourseDiscription(id);
        call.enqueue(new Callback<Courseins>() {
            @Override
            public void onResponse(Call<Courseins> call, Response<Courseins> response) {
                arrayList1= (ArrayList<Coursein>) response.body().getCourseins();
                course.setText(arrayList1.get(0).getcName());

            }

            @Override
            public void onFailure(Call<Courseins> call, Throwable t) {

            }
        });
    }
}