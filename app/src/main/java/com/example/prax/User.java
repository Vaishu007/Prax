package com.example.prax;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.prax.Ui.Contant;
import com.example.prax.Ui.Home;
import com.example.prax.Ui.Job;
import com.example.prax.Ui.Material;
import com.example.prax.Ui.Vedio;

public class User extends AppCompatActivity {
    String nam,img,dob,gen,add,clnam,clloc,branch,sem,ema,pho,pass,dur;
    int cid;
    private MeowBottomNavigation bottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        bottomNavigation = findViewById(R.id.bnb);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        bottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.job));
        bottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.book));
        bottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.ic_baseline_home_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(4,R.drawable.video));
        bottomNavigation.add(new MeowBottomNavigation.Model(5,R.drawable.content));
//        SharedPreferences share=User.this.getSharedPreferences("data",Context.MODE_PRIVATE);
//
//        nam=share.getString("name",null);
//        //img=share.getString("image",null);
//        dob=share.getString("dob",null);
//        gen=share.getString("gender",null);
//        add=share.getString("address",null);
//        clnam=share.getString("clgname",null);
//        clloc=share.getString("clglocation",null);
//        branch=share.getString("branch",null);
//        sem=share.getString("sem",null);
//        ema=share.getString("email",null);
//        pho=share.getString("phone",null);
//        pass=share.getString("password",null);
//        dur=share.getString("duration",null);
//        img=share.getString("image",null);
//        clloc=share.getString("clglocation",null);
//        cid=share.getInt("cid",0);
////        Uploadfile(img);
        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment fragment = null;

                switch (item.getId()) {
                    case 1:
                        fragment = new Job();
                        break;

                    case 2:
                        fragment = new Material();
                        break;

                    case 3:
                        fragment = new Home();
                        break;

                    case 4:
                        fragment = new Vedio();
                        break;
                    case 5:
                        fragment = new Contant();
                        break;
                }
                loadFragment(fragment);
            }
        });

        bottomNavigation.show(3,true);
        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {

            }
        });


        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // methods to control the operations that will
    // happen when user clicks on the action buttons
    @Override
    public boolean onOptionsItemSelected( @NonNull MenuItem item ) {

        switch (item.getItemId()){
            case R.id.profile:
                Intent intent=new Intent(User.this,Profile.class);
                startActivity(intent);
                break;
            case R.id.aboutus:
                Intent intent1=new Intent(User.this,Aboutus.class);
                startActivity(intent1);
                break;
            case R.id.contactus:

                Intent intent2=new Intent(User.this,Contactus.class);
                startActivity(intent2);
                break;
            case R.id.help:
                Intent i=new Intent(User.this,Help.class);
                startActivity(i);
                break;
            case R.id.upload:
//                Intent i1=new Intent(User.this,UploadPDF.class);
//                startActivity(i1);
                break;
            case R.id.logout:
                SharedPreferences share = getSharedPreferences("data", Context.MODE_PRIVATE);
                SharedPreferences.Editor ed = share.edit();
                ed.putBoolean("is_login",false);
                ed.apply();
                Intent i5 = new Intent(this,Login.class);
                startActivity(i5);
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
    private void loadFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame,fragment)
                .commit();
    }
//    public void Uploadfile(String mediapath){
//        //Log.e("name",mediapath);
//        File file=new File(img);
//
//        RequestBody na=RequestBody.create(MediaType.parse("text"),nam);
//        RequestBody gender=RequestBody.create(MediaType.parse("text"),gen);
//        RequestBody dat=RequestBody.create(MediaType.parse("text"),dob);
//        RequestBody ad=RequestBody.create(MediaType.parse("text"),add);
//        RequestBody clna=RequestBody.create(MediaType.parse("text"),clnam);
//        RequestBody cll=RequestBody.create(MediaType.parse("text"),clloc);
//        RequestBody br=RequestBody.create(MediaType.parse("text"),branch);
//        RequestBody se=RequestBody.create(MediaType.parse("text"),sem);
//        RequestBody em=RequestBody.create(MediaType.parse("text"),ema);
//        RequestBody ph=RequestBody.create(MediaType.parse("text"),pho);
//        RequestBody pas=RequestBody.create(MediaType.parse("text"),pass);
//        RequestBody ci=RequestBody.create(MediaType.parse("text"), String.valueOf(cid));
//        RequestBody du=RequestBody.create(MediaType.parse("text"),dur);
//
//        RequestBody img=RequestBody.create(MediaType.parse("multipart/form-data"),file);
//        MultipartBody.Part filetoupload=MultipartBody.Part.createFormData("uploaded_file",file.getName(),img);
//        Apiinterface apiinterface=Apiclient.praxware().create(Apiinterface.class);
//        Log.e("name1",file.getName());
//        Call<Regisjson> call=apiinterface.userins(filetoupload,na,gender,dat,em,ph,pas,ad,clna,cll,br,se,ci,du);
//        call.enqueue(new Callback<Regisjson>() {
//            @Override
//            public void onResponse(Call<Regisjson> call, Response<Regisjson> response) {
//                //Toast.makeText(User.this, "data saved", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Call<Regisjson> call, Throwable t) {
//
////                Toast.makeText(User.this, "fail"+t, Toast.LENGTH_LONG).show();
//            }
//        });
//
//    }
//

}
