package com.example.prax;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.prax.Adapter.ClistAda;
import com.example.prax.Json.Courseins;
import com.example.prax.Table.Coursein;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Course_type extends AppCompatActivity {
RecyclerView recyclerView;
ArrayList<Coursein> arrayList;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_type);
        recyclerView=findViewById(R.id.recycler_view);

        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        arrayList=new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Apiinterface apiinterface=Apiclient.praxware().create(Apiinterface.class);
        Call<Courseins> call=apiinterface.Coursetype(type);
        call.enqueue(new Callback<Courseins>() {
            @Override
            public void onResponse(Call<Courseins> call, Response<Courseins> response) {
                Log.e("error",type);
                arrayList = (ArrayList<Coursein>) response.body().getCourseins();
                ClistAda clistAda=new ClistAda(Course_type.this,arrayList);
                recyclerView.setAdapter(clistAda);
            }

            @Override
            public void onFailure(Call<Courseins> call, Throwable t) {

            }
        });

    }



}