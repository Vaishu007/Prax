package com.example.prax.Adapter;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prax.R;
import com.example.prax.Table.Content;


import java.io.File;
import java.util.ArrayList;

public class C_mrecyle extends RecyclerView.Adapter<C_mrecyle.Myclass> {
    Context context;
    ArrayList<Content> arrayList;
    AlertDialog.Builder alertDialog;
    private static final int MEGABYTE = 1024 * 1024;


    public C_mrecyle(Context context, ArrayList<Content> arrayList){
        this.context=context;
        this.arrayList=arrayList;
    }
    @NonNull
    @Override
    public Myclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cmateriallayout,parent,false);
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
        ImageButton b2;
        public Myclass(@NonNull View itemView) {
            super(itemView);
            t1=itemView.findViewById(R.id.c_chno);
            t2=itemView.findViewById(R.id.c_chname);
            t3=itemView.findViewById(R.id.c_name);
            b2=itemView.findViewById(R.id.c_download);
            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String Url = arrayList.get(getAdapterPosition()).getConDocument();
                    String name=arrayList.get(getAdapterPosition()).getConName();

                    alertDialog = new AlertDialog.Builder(context);
                    alertDialog.setTitle("Download")
                            .setMessage("You Want to Download this Image??")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    try {
                                        downloadTask2(Url,name);
                                    } catch (Exception e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            }).show();


                }
            });

        }
    }
    private boolean downloadTask2(String url , String name) throws Exception {
        try {
            File file = new File(Environment.getExternalStorageDirectory(), "Download");
            if (!file.exists()) {
                //noinspection ResultOfMethodCallIgnored
                file.mkdirs();
            }
            File result = new File(file.getAbsolutePath() + File.separator + name);
            DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
            request.setDestinationUri(Uri.fromFile(result));
            request.setMimeType("application/pdf");
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            request.setAllowedOverRoaming(false).setTitle(name);//for mp3 title
            request.setDescription(name);
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOCUMENTS,File.separator+name+".pdf");
            if (downloadManager != null) {
                downloadManager.enqueue(request);
            }

            MediaScannerConnection.scanFile(context, new String[]{result.toString()}, null,
                    new MediaScannerConnection.OnScanCompletedListener() {
                        public void onScanCompleted(String path, Uri uri) {
                            Log.i("tag >>>>", "on Downlaod check it");


                        }
                    });
        } catch (Exception e) {
            Log.i("tag >>>> ", e.toString());
            return false;
        }
        return true;
    }

}
