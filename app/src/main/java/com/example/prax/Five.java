package com.example.prax;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prax.Json.Regisjson;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Five extends Fragment implements BlockingStep {

    CardView cd,cd2;
    String nam,img,dob,gen,add,clnam,clloc,branch,sem,ema,pho,pass,dur;
    int cid;
Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_five, container,
                false);
        cd=v.findViewById(R.id.application);
        cd2=v.findViewById(R.id.web);
        context=getContext();

        cd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),Course_type.class);
                intent.putExtra("type","Application");
                startActivity(intent);
            }
        });
        cd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),Course_type.class);
                intent.putExtra("type","Web");
                startActivity(intent);
            }
        });

        return v;
    }




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
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {
//        Uploadfile(img);
        new Handler().postDelayed(new Runnable() {
                                      @Override
                                      public void run() {
                                          callback.goToNextStep();
                                          callback.getStepperLayout().hideProgress();
                                      }
                                  }
                ,2000L);
    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {


        Log.e("cid", String.valueOf(cid));


        new Handler().postDelayed(new Runnable() {
                                      @Override
                                      public void run() {
                                          SharedPreferences share=context.getSharedPreferences("data", Context.MODE_PRIVATE);
//                Boolean reg = share.getBoolean("is_regi",false);
//                if(reg){
                                          nam=share.getString("name",null);
                                          //img=share.getString("image",null);
                                          dob=share.getString("dob",null);
                                          gen=share.getString("gender",null);
                                          add=share.getString("address",null);
                                          clnam=share.getString("clgname",null);
                                          clloc=share.getString("clglocation",null);
                                          branch=share.getString("branch",null);
                                          sem=share.getString("sem",null);
                                          ema=share.getString("email",null);
                                          pho=share.getString("phone",null);
                                          pass=share.getString("password",null);
                                          dur=share.getString("duration",null);
                                          img=share.getString("image",null);
                                          clloc=share.getString("clglocation",null);
                                          cid=share.getInt("cid",0);
                                          if(cid==0){
                                              Toast.makeText(context, "Please select course", Toast.LENGTH_SHORT).show();
                                          }else {
                                              Uploadfile(img);
//                }

                                              callback.complete();
                                              callback.getStepperLayout().hideProgress();
                                          }
                                      }
                                  }
                ,2000L);

    }

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {
        callback.goToPrevStep();
    }
    private void Uploadfile(String mediapath) {
        File file=new File(mediapath);

        RequestBody na=RequestBody.create(MediaType.parse("text"),nam);
        RequestBody gender=RequestBody.create(MediaType.parse("text"),gen);
        RequestBody dat=RequestBody.create(MediaType.parse("text"),dob);
        RequestBody ad=RequestBody.create(MediaType.parse("text"),add);
        RequestBody clna=RequestBody.create(MediaType.parse("text"),clnam);
        RequestBody cll=RequestBody.create(MediaType.parse("text"),clloc);
        RequestBody br=RequestBody.create(MediaType.parse("text"),branch);
        RequestBody se=RequestBody.create(MediaType.parse("text"),sem);
        RequestBody em=RequestBody.create(MediaType.parse("text"),ema);
        RequestBody ph=RequestBody.create(MediaType.parse("text"),pho);
        RequestBody pas=RequestBody.create(MediaType.parse("text"),pass);
        RequestBody ci=RequestBody.create(MediaType.parse("text"), String.valueOf(cid));
        RequestBody du=RequestBody.create(MediaType.parse("text"),dur);

        RequestBody img=RequestBody.create(MediaType.parse("multipart/form-data"),file);
        MultipartBody.Part uploadfile=MultipartBody.Part.createFormData("uploaded_file",file.getName(),img);
        Apiinterface apiinterface=Apiclient.praxware().create(Apiinterface.class);
        Log.e("name1",file.getName());
        Call<Regisjson> call=apiinterface.userins(uploadfile,na,gender,dat,em,ph,pas,ad,clna,cll,br,se,ci,du);
        call.enqueue(new Callback<Regisjson>() {
            @Override
            public void onResponse(Call<Regisjson> call, Response<Regisjson> response) {
                SharedPreferences share = context.getSharedPreferences("data", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = share.edit();
                editor.putBoolean("is_regi",true);
                Toast.makeText(context, "data saved", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<Regisjson> call, Throwable t) {

                Toast.makeText(context, "fail"+t, Toast.LENGTH_LONG).show();
            }
        });

    }
}