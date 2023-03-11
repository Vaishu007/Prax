package com.example.prax;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.prax.Adapter.FaqAdapter;
import com.example.prax.Json.Faqjson;
import com.example.prax.Table.Faq;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Help extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Faq> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        recyclerView = findViewById(R.id.rvhelp);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrayList = new ArrayList<>();
        Apiinterface apiinterface = Apiclient.praxware().create(Apiinterface.class);
        Call<Faqjson> call = apiinterface.faqshow();
        call.enqueue(new Callback<Faqjson>() {
            @Override
            public void onResponse(Call<Faqjson> call, Response<Faqjson> response) {
                arrayList = (ArrayList<Faq>) response.body().getFaq();
                FaqAdapter faqAdapter = new FaqAdapter(Help.this,arrayList);
                recyclerView.setAdapter(faqAdapter);

            }

            @Override
            public void onFailure(Call<Faqjson> call, Throwable t) {

            }
        });

    }
}