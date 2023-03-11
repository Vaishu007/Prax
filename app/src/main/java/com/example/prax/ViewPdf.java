package com.example.prax;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.WindowManager;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ViewPdf extends AppCompatActivity {
    String uridata;
    PDFView pdfView;
    String Url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pdf);

        pdfView=findViewById(R.id.pdf);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE);

        Intent intent = getIntent();
        Url = intent.getStringExtra("url");

        new LOADURL().execute(Url);


    }
    class LOADURL extends AsyncTask<String, Void, InputStream> {
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog=new ProgressDialog(ViewPdf.this);
            progressDialog.setTitle("Please wait");
            progressDialog.setMessage("Fetching PDF from server...");
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

        }

        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream inputStream = null;
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection)
                        url.openConnection();
                if (urlConnection.getResponseCode() == 200) {

                    inputStream = new
                            BufferedInputStream(urlConnection.getInputStream());

                }
            } catch (IOException e) {

                return null;
            }
            return inputStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            pdfView.fromStream(inputStream)
                    .defaultPage(0)
                    .enableAnnotationRendering(true)
                    .spacing(10) // in dp
                    .enableAnnotationRendering(false)
                    .enableAntialiasing(true)
                    .load();
            progressDialog.dismiss();
        }
    }
}