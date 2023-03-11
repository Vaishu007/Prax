package com.example.prax;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class C_sql extends AppCompatActivity {

    ImageButton btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_csql);
        btn = findViewById(R.id.btn);

        btn.setOnClickListener(view -> {
            Intent intent = new Intent(C_sql.this,ViewPdf.class);
            intent.putExtra("url","");
            startActivity(intent);
        });

    }
}