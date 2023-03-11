package com.example.prax;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class C_quize extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cquize);
    }
    public void init(View view){
        Intent intent = new Intent(this, QuestionActivity.class);
        startActivity(intent);
        finish();
    }
}