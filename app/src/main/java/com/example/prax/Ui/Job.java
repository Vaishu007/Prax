package com.example.prax.Ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.prax.Adapter.Jobrecycle;
import com.example.prax.Apiclient;
import com.example.prax.Apiinterface;
import com.example.prax.Json.Companyjson;
import com.example.prax.R;
import com.example.prax.Table.Company;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Job extends Fragment {

    RecyclerView rv;
    ArrayList<Company> arrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this

        View v= inflater.inflate(R.layout.fragment_job, container, false);
        rv=v.findViewById(R.id.jobrv);
        arrayList = new ArrayList<>();
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        Apiinterface apiinterface = Apiclient.praxware().create(Apiinterface.class);
        Call<Companyjson> call = apiinterface.companyshow();
        call.enqueue(new Callback<Companyjson>() {
            @Override
            public void onResponse(Call<Companyjson> call, Response<Companyjson> response) {
                arrayList = (ArrayList<Company>) response.body().getCompanyjson();
                Jobrecycle jobrecycle = new Jobrecycle(getContext(),arrayList);
                rv.setAdapter(jobrecycle);
            }

            @Override
            public void onFailure(Call<Companyjson> call, Throwable t) {

            }
        });

        return v;
    }
}