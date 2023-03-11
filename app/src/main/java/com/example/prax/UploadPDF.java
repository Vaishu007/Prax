package com.example.prax;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadPDF extends AppCompatActivity {

    Button b1,b2;
    String uridata;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_pdf);

        b1 = findViewById(R.id.button5);
        b2 = findViewById(R.id.button7);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intentPDF = new Intent(Intent.ACTION_GET_CONTENT);
                intentPDF.setType("application/pdf");
                intentPDF.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(Intent.createChooser(intentPDF, "Select Picture"), 100);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Toast.makeText(UploadPDF.this, "Click", Toast.LENGTH_SHORT).show();
                    File file = new File(uridata);
                    RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                    MultipartBody.Part fileupload = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
                    RequestBody name = RequestBody.create(MediaType.parse("text/plain"), "chapter1");
                    RequestBody branch = RequestBody.create(MediaType.parse("text/plain"), "Computer");
                    RequestBody sem = RequestBody.create(MediaType.parse("text/plain"), "1");
                    RequestBody subject = RequestBody.create(MediaType.parse("text/plain"), "Android");
                    RequestBody code = RequestBody.create(MediaType.parse("text/plain"), "3170701");
                    RequestBody type = RequestBody.create(MediaType.parse("text/plain"), "Paper");
                    Apiinterface apIinterface = Apiclient.praxware().create(Apiinterface.class);
                    Call<ServerResponce> call = apIinterface.fileupload(fileupload,name,branch,sem,subject,code,type);
                    call.enqueue(new Callback<ServerResponce>() {
                        @Override
                        public void onResponse(Call<ServerResponce> call, Response<ServerResponce> response) {
                            Toast.makeText(UploadPDF.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<ServerResponce> call, Throwable t) {
                            Toast.makeText(UploadPDF.this, "fail"+t, Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (Exception e) {
                    Toast.makeText(UploadPDF.this, "Error" + e, Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            try {
                Uri uri = data.getData();
                uridata = Path.getPathFromUri(UploadPDF.this, uri);
                Toast.makeText(this, "PATH" + uridata, Toast.LENGTH_SHORT).show();
                Log.e("IMGPATH", uridata);
            } catch (Exception e) {
                Log.e("IMGPATH", String.valueOf(e));
            }

        }
    }

}