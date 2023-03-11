package com.example.prax;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.util.regex.Pattern;


public class Four extends Fragment implements BlockingStep {

EditText email,phone,password,repassword;
//String pattern = "r'^([6-9]{1})?[0-9]{9}$'";
private static final Pattern PASSWORD_PATTERN =
        Pattern.compile("^" +
                "(?=.*[@#$%^&+=])" +     // at least 1 special character
                "(?=\\S+$)" +            // no white spaces
                ".{6,}" +                // at least 6 characters
                "$");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_four, container, false);
        email=v.findViewById(R.id.email);
        phone=v.findViewById(R.id.phoneno);
        password=v.findViewById(R.id.password);
        repassword=v.findViewById(R.id.repassword);

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

        String em,ph,pass,repass;
        em=email.getText().toString();
        ph=phone.getText().toString();
        pass=password.getText().toString();
        repass=repassword.getText().toString();


            SharedPreferences share = getContext().getSharedPreferences("data", Context.MODE_PRIVATE);
            SharedPreferences.Editor ed = share.edit();

            ed.putString("email",em);
            ed.putString("phone",ph);
            ed.putString("password",pass);
            ed.putBoolean("four",false);
            ed.apply();

            repassword.setError("Password is not Matched");
            repassword.setText("");

        new Handler().postDelayed(new Runnable() {
//
                                      @Override
                                      public void run() {
                                          if (em.equals("")||em==null|| !Patterns.EMAIL_ADDRESS.matcher(em).matches()){
                                              Toast.makeText(getContext(), "Please Enter valid Email", Toast.LENGTH_SHORT).show();
                                          } else if (ph.equals("")||ph==null ||!Pattern.matches("^([6-9]{1})?[0-9]{9}$",ph)) {
                                              Toast.makeText(getContext(), "Please Enter valid Phone number", Toast.LENGTH_SHORT).show();
                                          } else if (pass.equals("")||pass==null) {
                                              Toast.makeText(getContext(), "Please Enter Password", Toast.LENGTH_SHORT).show();
                                          } else if (!PASSWORD_PATTERN.matcher(pass).matches()) {
                                              password.setFocusable(true);
                                              password.setError("Password too weak");
                                              Toast.makeText(getContext(), "password should contain at least 1 special character and no white spaces and at least 6 characters", Toast.LENGTH_LONG).show();
                                          } else if (!repass.matches(pass)) {
                                              repassword.setError("Password is not Matched");
                                              repassword.setText("");
                                          } else {

                                          callback.goToNextStep();
                                          callback.getStepperLayout().hideProgress();
                                          }
                                      }
                                  }
                ,2000L);
//
    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {

    }

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {
        callback.goToPrevStep();
    }
}