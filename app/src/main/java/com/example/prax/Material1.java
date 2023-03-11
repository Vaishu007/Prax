package com.example.prax;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.prax.Adapter.Notesrecycle;
import com.example.prax.Json.Materialjson;
import com.example.prax.Table.Material;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Material1 extends AppCompatActivity {
    RecyclerView rv;
    ArrayList<Material> marrayList;
    String type,branch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material1);
        rv=findViewById(R.id.mrv);
        marrayList = new ArrayList<>();
        Intent i=getIntent();
        type=i.getStringExtra("type");
        branch=i.getStringExtra("branch");
        rv.setLayoutManager(new LinearLayoutManager(Material1.this));
        Apiinterface apiinterface=Apiclient.praxware().create(Apiinterface.class);
        Call<Materialjson> call=apiinterface.materialshow(type,branch);
        call.enqueue(new Callback<Materialjson>() {
            @Override
            public void onResponse(Call<Materialjson> call, Response<Materialjson> response) {
                marrayList= (ArrayList<Material>) response.body().getMaterial();
                Notesrecycle notesrecycle=new Notesrecycle(Material1.this,marrayList);
                rv.setAdapter(notesrecycle);
            }

            @Override
            public void onFailure(Call<Materialjson> call, Throwable t) {
                Toast.makeText(Material1.this, "fail"+t, Toast.LENGTH_SHORT).show();

            }
        });
    }
}