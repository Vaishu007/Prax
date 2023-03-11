package com.example.prax;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prax.Json.Regisjson;
import com.example.prax.Table.Praxregi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    EditText e, p;
    TextView re;
    String em, pa;
    Button btn;
    ArrayList<Praxregi> arrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        e = findViewById(R.id.email);
        p = findViewById(R.id.pass);
        re = findViewById(R.id.reg);
        btn = findViewById(R.id.loginbtn);
        SharedPreferences share = Login.this.getSharedPreferences("data", Context.MODE_PRIVATE);

        Boolean log = share.getBoolean("is_login",false);
        if(log){
            Intent in = new Intent(Login.this, User.class);
            startActivity(in);
            finish();
        }
        re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Login.this,Registration.class);
                startActivity(i);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                em = e.getText().toString();
                pa = p.getText().toString();
                arrayList = new ArrayList<>();
                if (em.equals("") ||em==null){e.setError("enter email");}
                else if( pa.equals("")||pa==null) {
                   p.setError("enter password");
                } else {
                    Apiinterface apiinterface = Apiclient.praxware().create(Apiinterface.class);
                    Call<Regisjson> call = apiinterface.user(em, pa);
                    call.enqueue(new Callback<Regisjson>() {
                        @Override
                        public void onResponse(Call<Regisjson> call, Response<Regisjson> response) {
                            arrayList = (ArrayList<Praxregi>) response.body().getPraxregi();

                            String email = arrayList.get(0).getuEmail();
                            String password = arrayList.get(0).getuPassword();
                            if (em.matches(email) || pa.matches(password)) {

                                SharedPreferences share = Login.this.getSharedPreferences("data", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = share.edit();
                               editor.putBoolean("is_login", true);
                                editor.apply();

                                Intent in = new Intent(Login.this, splashupload.class);
                                startActivity(in);

                            }
//
                            else if(!em.equals(email)){
                                e.setError("email is wrong");
                            }
                            else{
                                p.setError("password is wrong");
                            }

                        }

                        @Override
                        public void onFailure(Call<Regisjson> call, Throwable t) {

                            Toast.makeText(Login.this, "" + t, Toast.LENGTH_LONG).show();
                        }
                    });

                }
            }


        });
    }
}
