package com.example.prax;

import android.app.DatePickerDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.os.Handler;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;
import timber.log.Timber;


public class One extends Fragment implements BlockingStep {
    EditText fname, lname;
    TextView dob;
    CircleImageView iv;
    RadioGroup gender;
    RadioButton m, f;
    String fn,ln;

    String gen, url, name, date;
    Calendar c = Calendar.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_one, null);
        fname = v.findViewById(R.id.firstname);
        lname = v.findViewById(R.id.lastname);
        dob = v.findViewById(R.id.dob);
        iv = v.findViewById(R.id.userimage);
        gender = v.findViewById(R.id.rg);
        m = v.findViewById(R.id.male);
        f = v.findViewById(R.id.female);

        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getContext(), setdate, c.get(Calendar.YEAR), c.get(Calendar.MONDAY), c.get(Calendar.DAY_OF_MONTH)).show();
            }
        });



        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.male:
                        gen = m.getText().toString();
                        break;
                    case R.id.female:
                        gen = f.getText().toString();
                }
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


        return v;

    }


    private DatePickerDialog.OnDateSetListener setdate = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            int i3 = i1 + 1;
            date = i2 + "/" + i3 + "/" + i;
            dob.setText(date);
        }
    };

    @Nullable
    @Override
    public VerificationError verifyStep() {
        return null;
    }

    @Override
    public void onSelected() {
    }

    @Override
    public void onError(@NonNull VerificationError error) {
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            Uri uri = data.getData();
            url = getPath(getContext(), uri);
            iv.setImageURI(uri);

        }
    }


    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {

        fn=fname.getText().toString();
        ln=lname.getText().toString();
        name = fn+" "+ln;


        SharedPreferences share = getContext().getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = share.edit();
        editor.putString("name", name);
        editor.putString("gender", gen);
        editor.putString("dob", date);
        editor.putString("image", url);
        Log.e("img", url);
        editor.apply();
        Log.e("name1",fn);



        new Handler().postDelayed(new Runnable() {
                                      @Override
                                      public void run() {
                                          if (url==null||url.equals("")) {
                                              Toast.makeText(getContext(), "Please select image", Toast.LENGTH_SHORT).show();
                                          } else if (fn == null||fn.equals("")) {
                                              fname.setError("Required");
                                              Toast.makeText(getContext(), "Please Enter Name", Toast.LENGTH_SHORT).show();
                                          } else if(ln == null||ln.equals("")){
                                              lname.setError("Required");
                                              Toast.makeText(getContext(), "Please Enter Name", Toast.LENGTH_SHORT).show();
                                          } else if (gen == null || gen.equals("")) {
                                              Toast.makeText(getContext(), "Please select gender", Toast.LENGTH_SHORT).show();
                                          } else if (date == null || date.equals("")) {
                                              dob.setError("Please select Date of birth");
                                              Toast.makeText(getContext(), "Please select date of birth", Toast.LENGTH_SHORT).show();
                                          }else {
                                              callback.goToNextStep();
                                              callback.getStepperLayout().hideProgress();
                                          }
                                      }

                                  }
                , 2000L);
    }


    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {

    }

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {

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