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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.stepstone.stepper.BlockingStep;

import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;


public class Third extends Fragment implements BlockingStep {

    EditText clgname,clglocation;
    Spinner branch,sem;
    String Course[] = {"Branch","Diploma","BCA","MCA","BE","ME"};
    String Sem[] = {"Sem","1","2","3","4","5","6","7","8"};
    String courseselect;
    String semselect;
    String name,location;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_third, container, false);
        clgname=v.findViewById(R.id.clgname);
        clglocation=v.findViewById(R.id.clglocation);
        branch=v.findViewById(R.id.branch);
        sem=v.findViewById(R.id.sem);


        ArrayAdapter<String> courseadapter = new ArrayAdapter<>(getContext(), R.layout.color_spinner, Course);
        branch.setAdapter(courseadapter);

        ArrayAdapter<String> semadapter = new ArrayAdapter<>(getContext(), R.layout.color_spinner, Sem);
        sem.setAdapter(semadapter);

        branch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                courseselect = adapterView.getItemAtPosition(i).toString();
                branch.setSelection(adapterView.getSelectedItemPosition());


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                semselect = adapterView.getItemAtPosition(i).toString();
                sem.setSelection(adapterView.getSelectedItemPosition()
                );

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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

        name = clgname.getText().toString();
        location = clglocation.getText().toString();



        SharedPreferences share = getContext().getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = share.edit();

        ed.putString("clgname",name);
        ed.putString("clglocation",location);
        ed.putString("branch",courseselect);
        ed.putString("sem",semselect);
        ed.putBoolean("three",false);
        ed.apply();
         new Handler().postDelayed(new Runnable() {
                                      @Override
                                      public void run() {

                                          if(name.equals("")||name==null){
                                              Toast.makeText(getContext(), "Please Enter name", Toast.LENGTH_SHORT).show();
                                          }else if(location.equals("")||location==null){
                                              Toast.makeText(getContext(), "Please Enter name", Toast.LENGTH_SHORT).show();
                                          }else if(courseselect.equals("Branch")){
                                              Toast.makeText(getContext(), "Please Enter name", Toast.LENGTH_SHORT).show();
                                          }else if(name.equals("Sem")){
                                              Toast.makeText(getContext(), "Please Enter name", Toast.LENGTH_SHORT).show();
                                          }

                                          else {
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