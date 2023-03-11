package com.example.prax;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.prax.Json.Regisjson;
import com.example.prax.Table.Praxregi;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile_update extends AppCompatActivity {

    CircleImageView iv;
    EditText name,email,contact,pass,address;
    String na,em,con,pas,add,id,url;
    ArrayList<Praxregi> arrayList;
    Button btn;
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[@#$%^&+=])" +     // at least 1 special character
                    "(?=\\S+$)" +            // no white spaces
                    ".{6,}" +                // at least 6 characters
                    "$");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_update);
        iv=findViewById(R.id.userimage);
        name=findViewById(R.id.upname);
        email=findViewById(R.id.upemail);
        contact=findViewById(R.id.upcontact);
        pass=findViewById(R.id.uppassword);
        address=findViewById(R.id.upaddress);
        btn=findViewById(R.id.profileupdate);

        arrayList=new ArrayList<>();
        Intent intent=getIntent();
        id=intent.getStringExtra("id");

        Apiinterface apiinterface=Apiclient.praxware().create(Apiinterface.class);
        Call<Regisjson> call=apiinterface.profileshow(id);
        call.enqueue(new Callback<Regisjson>() {
            @Override
            public void onResponse(Call<Regisjson> call, Response<Regisjson> response) {
                arrayList= (ArrayList<Praxregi>) response.body().getPraxregi();
                Picasso.get().load(arrayList.get(0).getuImage()).into(iv);
                name.setText(arrayList.get(0).getuName());
                email.setText(arrayList.get(0).getuEmail());
                contact.setText(arrayList.get(0).getuContact());
                pass.setText(arrayList.get(0).getuPassword());
                address.setText(arrayList.get(0).getuAddress());
            }

            @Override
            public void onFailure(Call<Regisjson> call, Throwable t) {

            }
        });
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 100);

            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                na=name.getText().toString();
                em=email.getText().toString();
                con=contact.getText().toString();
                pas=pass.getText().toString();
                add=address.getText().toString();
                if (url==null||url.equals("")) {
                    Toast.makeText(Profile_update.this, "Please select image", Toast.LENGTH_SHORT).show();
                } else if (na == null||na.equals("")) {
                    name.setError("Required");
                    Toast.makeText(Profile_update.this, "Please Enter Name", Toast.LENGTH_SHORT).show();
                }else if (con.equals("")||con==null ||!Pattern.matches("^([6-9]{1})?[0-9]{9}$",con)) {
                    Toast.makeText(Profile_update.this, "Please Enter valid Phone number", Toast.LENGTH_SHORT).show();
                } else if (em.equals("")||em==null|| !Patterns.EMAIL_ADDRESS.matcher(em).matches()){
                    Toast.makeText(Profile_update.this, "Please Enter valid Email", Toast.LENGTH_SHORT).show();
                }   else if (add.equals("")||add==null) {
                    address.setError("Required");
                }else if (pas.equals("")||pas==null) {
                    Toast.makeText(Profile_update.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                } else if (!PASSWORD_PATTERN.matcher(pas).matches()) {
                    pass.setFocusable(true);
                    pass.setError("Password too weak");
                    Toast.makeText(Profile_update.this, "password should contain at least 1 special character and no white spaces and at least 6 characters", Toast.LENGTH_LONG).show();
                }else {
                    Update(url);
                }
            }
        });


    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            Uri uri = data.getData();
            url = getPath(Profile_update.this, uri);
            iv.setImageURI(uri);

        }

    }
    public  void Update(String mediapath){
        Map<String, RequestBody> map=new HashMap<>();
        File file=new File(mediapath);

        RequestBody name=RequestBody.create(MediaType.parse("text"),na);
        RequestBody ema=RequestBody.create(MediaType.parse("text"),em);
        RequestBody cont=RequestBody.create(MediaType.parse("text"),con);
        RequestBody passw=RequestBody.create(MediaType.parse("text"),pas);
        RequestBody addres=RequestBody.create(MediaType.parse("text"),add);
        RequestBody uid=RequestBody.create(MediaType.parse("text"),id);


        RequestBody img=RequestBody.create(MediaType.parse("multipart/form-data"),file);
        MultipartBody.Part filetoupload=MultipartBody.Part.createFormData("uploaded_file", file.getName(),img);
        Apiinterface apiinterface=Apiclient.praxware().create(Apiinterface.class);
        Call<ServerResponce> call=apiinterface.profileupdate(filetoupload,name,ema,cont,passw,addres,uid);
        call.enqueue(new Callback<ServerResponce>() {
            @Override
            public void onResponse(Call<ServerResponce> call, Response<ServerResponce> response) {

                Intent intent =new Intent(Profile_update.this,Profile.class);
              startActivity(intent);
            }

            @Override
            public void onFailure(Call<ServerResponce> call, Throwable t) {
                Toast.makeText(Profile_update.this, ""+t, Toast.LENGTH_SHORT).show();
            }
        });





    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static String getPath(final Context context, final Uri uri) {
        //check here to KITKAT or new version
        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {

            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            }

            //DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }

            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {

            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();

            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context       The context.
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    public static String getDataColumn(
            Context context, Uri uri, String selection,
            String[] selectionArgs
    ) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null
            );
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }


}