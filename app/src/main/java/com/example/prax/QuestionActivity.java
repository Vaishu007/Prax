package com.example.prax;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prax.Adapter.Quizadapter;
import com.example.prax.Json.Examjson;
import com.example.prax.Table.Exam;
import com.example.prax.Table.Question;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionActivity extends AppCompatActivity  {

    ArrayList<Exam> arrayList;

    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

       rv = findViewById(R.id.rvquiz);
        arrayList=new ArrayList<>();
        rv.setLayoutManager(new LinearLayoutManager(QuestionActivity.this));

        Apiinterface apiinterface = Apiclient.praxware().create(Apiinterface.class);
        Call<Examjson> call = apiinterface.examshow();
        call.enqueue(new Callback<Examjson>() {
            @Override
            public void onResponse(Call<Examjson> call, Response<Examjson> response) {
                arrayList = (ArrayList<Exam>) response.body().getExam();
                Log.e("array",arrayList.get(0).geteOpt1());
                Quizadapter quizadapter=new Quizadapter(QuestionActivity.this,arrayList);
                rv.setAdapter(quizadapter);


            }

            @Override
            public void onFailure(Call<Examjson> call, Throwable t) {

                Toast.makeText(QuestionActivity.this, "fail" + t, Toast.LENGTH_SHORT).show();
            }
        });

    }
}