package com.example.prax;

import static com.example.prax.R.id.resumeadd;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prax.Json.JobapplyJson;
import com.example.prax.Json.Regisjson;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Resumeupload extends AppCompatActivity {

    ImageButton add;
    String comid;
    String uridata;
    TextView filename;
    CheckBox cb;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumeupload);
        add = findViewById(resumeadd);
        filename = findViewById(R.id.resumefile);
        Intent intent = getIntent();
        comid = intent.getStringExtra("comid");
        cb=findViewById(R.id.checkBox);
        SharedPreferences share = getSharedPreferences("data", Context.MODE_PRIVATE);
        id = share.getInt("id", 0);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentPDF = new Intent(Intent.ACTION_GET_CONTENT);
                intentPDF.setType("application/pdf");
                intentPDF.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(Intent.createChooser(intentPDF, "Select Resume"), 100);
                        }
        });
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Upload(uridata);
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            try {
                Uri uri = data.getData();
                uridata = Path.getPathFromUri(Resumeupload.this, uri);

                Toast.makeText(this, "PATH" + uridata, Toast.LENGTH_SHORT).show();
                Log.e("IMGPATH", uridata);

            }catch (Exception e){

            }
        }
    }

    void Upload(String uridata) {
        try {
            File file = new File(uridata);
            String filen = file.getName();
            filename.setText(filen);
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part fileupload = MultipartBody.Part.createFormData("file", file.getName(), requestBody);

            RequestBody compid = RequestBody.create(MediaType.parse("text/plain"), comid);
            RequestBody uid = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(id));

           Apiinterface apiinterface=Apiclient.praxware().create(Apiinterface.class);
           Call<JobapplyJson> call =apiinterface.apply(fileupload,compid,uid);
           call.enqueue(new Callback<JobapplyJson>() {
               @Override
               public void onResponse(Call<JobapplyJson> call, Response<JobapplyJson> response) {
                   Toast.makeText(Resumeupload.this, "applied", Toast.LENGTH_SHORT).show();
               }

               @Override
               public void onFailure(Call<JobapplyJson> call, Throwable t) {

                   Toast.makeText(Resumeupload.this, ""+t, Toast.LENGTH_SHORT).show();
               }
           });

        } catch (Exception e) {

            Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
        }

    }
   }