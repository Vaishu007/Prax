package com.example.prax;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.prax.Adapter.C_uirecycle;
import com.example.prax.Json.Contentjson;
import com.example.prax.Table.Content;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class C_ui extends AppCompatActivity {
    RecyclerView rv;
    String type="Ui";
    ArrayList<Content> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cui);
        rv=findViewById(R.id.cuirv);
        arrayList=new ArrayList<>();
        rv.setLayoutManager(new LinearLayoutManager(this));
        Apiinterface apiinterface=Apiclient.praxware().create(Apiinterface.class);
        Call<Contentjson> call=apiinterface.contentshow(type);
        call.enqueue(new Callback<Contentjson>() {
            @Override
            public void onResponse(Call<Contentjson> call, Response<Contentjson> response) {
                arrayList= (ArrayList<Content>) response.body().getContent();
                C_uirecycle c_mrecyle=new C_uirecycle(C_ui.this,arrayList);
                rv.setAdapter(c_mrecyle);
            }


            @Override
            public void onFailure(Call<Contentjson> call, Throwable t) {

            }
        });

    }
}