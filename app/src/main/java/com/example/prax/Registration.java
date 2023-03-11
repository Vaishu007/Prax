package com.example.prax;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.prax.Adapter.StepperAdapter;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

public class Registration extends AppCompatActivity implements StepperLayout.StepperListener {
    StepperLayout stepperLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        SharedPreferences share = Registration.this.getSharedPreferences("data", Context.MODE_PRIVATE);

        Boolean reg = share.getBoolean("is_regi",false);
        if(reg){
            Intent in = new Intent(Registration.this, Login.class);
            startActivity(in);
            finish();
        }
        ActionBar actionBar = getSupportActionBar();

        actionBar.hide();

        stepperLayout=findViewById(R.id.stepper);
        StepperAdapter stepperAdapter=new StepperAdapter(getSupportFragmentManager(),getApplicationContext());
        stepperLayout.setAdapter(stepperAdapter);
        stepperLayout.setListener(this);


    }
    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount()>0){
            getSupportFragmentManager().popBackStack();
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public void onCompleted(View completeButton) {


        Intent intent=new Intent(Registration.this,Login.class);
        startActivity(intent);

    }

    @Override
    public void onError(VerificationError verificationError) {

    }

    @Override
    public void onStepSelected(int newStepPosition) {


    }

    @Override
    public void onReturn() {

    }
}