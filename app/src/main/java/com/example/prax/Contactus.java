package com.example.prax;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.prax.Json.Feedbackjson;
import com.hsalf.smilerating.SmileRating;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Contactus extends AppCompatActivity {
    SmileRating sr;
    ImageButton btn;
    EditText e;
    String i="2",feed,rate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);
        sr=findViewById(R.id.smile);
        btn=findViewById(R.id.contactbtn);
        e=findViewById(R.id.feedback);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Contactus.this, ""+sr.getSelectedSmile(), Toast.LENGTH_SHORT).show();

                Log.e("id",i);
                feed=e.getText().toString();
                rate= String.valueOf(sr.getSelectedSmile());
                Apiinterface apiinterface=Apiclient.praxware().create(Apiinterface.class);
                Call<Feedbackjson> call=apiinterface.feedbackins(i,feed,rate);
                call.enqueue(new Callback<Feedbackjson>() {
                    @Override
                    public void onResponse(Call<Feedbackjson> call, Response<Feedbackjson> response) {
                        Toast.makeText(Contactus.this, "feedback saved", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Feedbackjson> call, Throwable t) {
                        Toast.makeText(Contactus.this, "fail", Toast.LENGTH_SHORT).show();

                        Log.e("fail",""+t);
                    }
                });

            }
        });

    }
}