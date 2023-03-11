package com.example.prax;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;


public class Second extends Fragment  implements BlockingStep {
    EditText hno,hname,street,landmark,town,pincode;
    String shno,shname,sstreet,slandmark,stown,spincode;

    String address;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_second, container, false);
        hno=v.findViewById(R.id.hno);
        hname=v.findViewById(R.id.hname);
        street=v.findViewById(R.id.sname);
        landmark=v.findViewById(R.id.lname);
        town=v.findViewById(R.id.tname);
        pincode=v.findViewById(R.id.pincode);



        return  v;
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
        Toast.makeText(getContext(), error.getErrorMessage(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {
        shno = hno.getText().toString();
        shname = hname.getText().toString();
        sstreet = street.getText().toString();
        slandmark = landmark.getText().toString();
        stown = town.getText().toString();
        spincode = pincode.getText().toString();

        address = shno+", "+shname+", "+sstreet+", "+slandmark+", "+stown+"-"+spincode;


        SharedPreferences share = getContext().getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = share.edit();

        ed.putString("address",address);
        ed.putBoolean("second",true);
        ed.apply();

        new Handler().postDelayed(new Runnable() {
                                      @Override
                                      public void run() {
                                          if(shno.equals("")||shno==null){
                                              Toast.makeText(getContext(), "Please Enter House no.", Toast.LENGTH_SHORT).show();
                                          } else if (shname.equals("")||shname==null) {
                                              Toast.makeText(getContext(), "Please Enter Socity name", Toast.LENGTH_SHORT).show();
                                          } else if (sstreet.equals("")||sstreet==null) {
                                              Toast.makeText(getContext(), "Please Enter Area name", Toast.LENGTH_SHORT).show();
                                          } else if (slandmark.equals("")||slandmark==null) {
                                              Toast.makeText(getContext(), "Please Enter landmark", Toast.LENGTH_SHORT).show();
                                          } else if (stown.equals("")||stown==null) {
                                              Toast.makeText(getContext(), "Please Enter city", Toast.LENGTH_SHORT).show();
                                          } else if (spincode.equals("")||spincode==null) {
                                              Toast.makeText(getContext(), "Please Enter Pincode", Toast.LENGTH_SHORT).show();
                                          }else {
                                              callback.goToNextStep();
                                              callback.getStepperLayout().hideProgress();
                                          }
                                      }
                                  }
                ,2000L);


    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {

    }

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {
        callback.goToPrevStep();

    }
}