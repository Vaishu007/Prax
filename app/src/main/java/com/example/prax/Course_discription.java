package com.example.prax;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prax.Adapter.ClistAda;
import com.example.prax.Json.Courseins;
import com.example.prax.Table.Coursein;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Course_discription extends AppCompatActivity {

    CircleImageView imageView,timg;
    TextView name,des,tname;
    ArrayList<Coursein> arrayList;
    Spinner spinner;
    String Duration[] = {"Course_duration","15 days","1 month","3 months","6 months","1 year"};
    String dur;
    Button select;
    AlertDialog.Builder builder;
    String cname;
    int cid;
    View vlayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_discription);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        name = findViewById(R.id.cdname);
        des = findViewById(R.id.cddescription);
        imageView = findViewById(R.id.cdimg);
        spinner=findViewById(R.id.duration);
        select=findViewById(R.id.cdbutton);
        arrayList = new ArrayList<>();
        builder = new AlertDialog.Builder(this);

        LayoutInflater layoutInflater=getLayoutInflater();
        vlayout=layoutInflater.inflate(R.layout.custom_toast,(ViewGroup)findViewById(R.id.custom));
        timg=vlayout.findViewById(R.id.toastimg);
        tname=vlayout.findViewById(R.id.toasttext);

        ArrayAdapter<String> duration = new ArrayAdapter<String >(this, R.layout.color_spinner,Duration);
        spinner.setAdapter(duration);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                dur = adapterView.getItemAtPosition(i).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Apiinterface apiinterface=Apiclient.praxware().create(Apiinterface.class);
        Call<Courseins> call=apiinterface.CourseDiscription(id);
        call.enqueue(new Callback<Courseins>() {
            @Override
            public void onResponse(Call<Courseins> call, Response<Courseins> response) {
                arrayList = (ArrayList<Coursein>) response.body().getCourseins();
                Log.e("error",id);
                name.setText(arrayList.get(0).getcName());
                des.setText(arrayList.get(0).getcDetail());
                Picasso.get().load(arrayList.get(0).getcImage()).into(imageView);
                cid = Integer.parseInt(arrayList.get(0).getcId());
                Picasso.get().load(arrayList.get(0).getcImage()).into(timg);
                tname.setText(arrayList.get(0).getcName());

            }

            @Override
            public void onFailure(Call<Courseins> call, Throwable t) {

            }
        });

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cname = name.getText().toString();


                builder.setMessage("Do you want to choose this course ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                SharedPreferences share = getApplicationContext().getSharedPreferences("data", Context.MODE_PRIVATE);
                                SharedPreferences.Editor ed = share.edit();

                                ed.putInt("cid", cid);
                                ed.putString("duration",dur);
                                ed.apply();

                                Toast toast= Toast.makeText(Course_discription.this,"Toast:Gravity.TOP",Toast.LENGTH_LONG);
                                toast.setGravity(Gravity.FILL_HORIZONTAL,0,520);
                                toast.setView(vlayout);
                                toast.setDuration(Toast.LENGTH_LONG);
                                toast.show();
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();
                                Toast.makeText(getApplicationContext(), "you choose no action for alertbox",
                                        Toast.LENGTH_SHORT).show();
                            }


                        });
                builder.create().show();

            }
        }
        );



    }
}
