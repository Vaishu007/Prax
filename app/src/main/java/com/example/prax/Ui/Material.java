package com.example.prax.Ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prax.Adapter.Viewpageadpter;
import com.example.prax.Material1;
import com.example.prax.NotesFragment;
import com.example.prax.PapersFragment;
import com.example.prax.R;
import com.google.android.material.tabs.TabLayout;

public class Material extends Fragment {

    Button btn,btn2;
    TextView tv;

    Spinner branch;
    String courseselect;
    String Program[] = {"Program", "Diploma", "BCA", "MCA", "BE", "ME"};



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_material, container, false);
        branch = v.findViewById(R.id.spinner);
        btn = v.findViewById(R.id.not);
        btn2 = v.findViewById(R.id.paper);
        tv=v.findViewById(R.id.tv);
        ArrayAdapter<String> courseadapter = new ArrayAdapter<>(getContext(), R.layout.color_spinner, Program);
        branch.setAdapter(courseadapter);
        branch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                courseselect = branch.getSelectedItem().toString();
                branch.setSelection(branch.getSelectedItemPosition());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(courseselect!="Program") {
                    Intent i = new Intent(getContext(), Material1.class);
                    i.putExtra("branch", courseselect);
                    i.putExtra("type", "Notes");
                    startActivity(i);
                }
            }

        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (courseselect != "Program") {
                    Intent i = new Intent(getContext(), Material1.class);
                    i.putExtra("branch", courseselect);
                    i.putExtra("type", "Paper");
                    startActivity(i);
                }
            }
        });
        return v;
    }
}