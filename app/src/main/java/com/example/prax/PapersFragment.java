package com.example.prax;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.prax.Adapter.Paperrecycle;
import com.example.prax.Json.Materialjson;
import com.example.prax.Table.Material;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PapersFragment extends Fragment {

    RecyclerView rv;
    String type="Paper";
    ArrayList<Material> marrayList;
    String branch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_papers, container, false);
        rv = v.findViewById(R.id.rvpapers);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("data", Context.MODE_PRIVATE);
        branch = sharedPreferences.getString("branch",null);

        marrayList = new ArrayList<>();
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        Apiinterface apiinterface = Apiclient.praxware().create(Apiinterface.class);
        Call<Materialjson> call=apiinterface.materialshow(type,branch);
        call.enqueue(new Callback<Materialjson>() {
            @Override
            public void onResponse(Call<Materialjson> call, Response<Materialjson> response) {
                marrayList= (ArrayList<Material>) response.body().getMaterial();
                Paperrecycle paperrecycle=new Paperrecycle(getContext(),marrayList);
                rv.setAdapter(paperrecycle);
            }

            @Override
            public void onFailure(Call<Materialjson> call, Throwable t) {

            }
        });


        return v;
    }
}