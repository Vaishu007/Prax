package com.example.prax.Adapter;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prax.R;
import com.example.prax.Table.Content;
import com.example.prax.ViewPdf;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class C_uirecycle extends RecyclerView.Adapter<C_uirecycle.Myclass> {
    Context context;
    ArrayList<Content> arrayList;
    AlertDialog.Builder alertDialog;
    private static final int MEGABYTE = 1024 * 1024;



    public C_uirecycle(Context context, ArrayList<Content> arrayList){
        this.context=context;
        this.arrayList=arrayList;
    }
    @NonNull
    @Override
    public Myclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cuilayout,parent,false);
        return new Myclass(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Myclass holder, int position) {
        holder.t1.setText(arrayList.get(position).getConChno());
        holder.t2.setText(arrayList.get(position).getConChname());
        holder.t3.setText(arrayList.get(position).getConName());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class Myclass extends RecyclerView.ViewHolder {
        TextView t1,t2,t3;
        ImageButton b1;
        public Myclass(@NonNull View itemView) {
            super(itemView);
            t1=itemView.findViewById(R.id.ui_chno);
            t2=itemView.findViewById(R.id.ui_chname);
            t3=itemView.findViewById(R.id.ui_name);
            b1=itemView.findViewById(R.id.ui_view);

            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, ViewPdf.class);
                    intent.putExtra("url",arrayList.get(getAdapterPosition()).getConDocument());
                    context.startActivity(intent);
                }
            });

        }
    }


}