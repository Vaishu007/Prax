package com.example.prax;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button b1,b2,b3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences share = MainActivity.this.getSharedPreferences("data", Context.MODE_PRIVATE);

        Boolean log = share.getBoolean("is_login",false);
        Boolean regi=share.getBoolean("is_regi",false);
        if(regi){
            Intent in = new Intent(MainActivity.this, Login.class);
            startActivity(in);
            finish();
        }
        if(log){
            Intent in = new Intent(MainActivity.this, User.class);
            startActivity(in);
            finish();
        }
        ActionBar actionBar = getSupportActionBar();

        actionBar.hide();

        b1=findViewById(R.id.button);
        b2=findViewById(R.id.button2);
        b3=findViewById(R.id.button3);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(MainActivity.this,Login.class);
                startActivity(i1);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(MainActivity.this,Registration.class);
                startActivity(i1);
            }
        });

    }
}