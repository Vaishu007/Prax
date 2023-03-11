package com.example.prax.Ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.prax.C_certificate;
import com.example.prax.C_challenge;
import com.example.prax.C_material;
import com.example.prax.C_quize;
import com.example.prax.C_sql;
import com.example.prax.C_ui;
import com.example.prax.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class Contant extends Fragment {

    CircleImageView iv1,iv2,iv3,iv4,iv5,iv6;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_contant, container, false);

        iv1 = v.findViewById(R.id.cm);
        iv2 = v.findViewById(R.id.chal);
        iv3 = v.findViewById(R.id.ui);
        iv4 = v.findViewById(R.id.quiz);
        iv5 = v.findViewById(R.id.sql);
        iv6 = v.findViewById(R.id.certificate);

        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(), C_material.class);
                startActivity(intent);

            }
        });
        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), C_challenge.class);
                startActivity(intent);
            }
        });
        iv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),C_ui.class);
                startActivity(intent);
            }
        });
        iv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), C_quize.class);
                startActivity(intent);
            }
        });
        iv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), C_sql.class);
                startActivity(intent);
            }
        });
        iv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), C_certificate.class);
                startActivity(intent);
            }
        });

        return v;
    }
}