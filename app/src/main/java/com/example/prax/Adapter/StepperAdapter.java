package com.example.prax.Adapter;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.example.prax.Five;
import com.example.prax.Four;
import com.example.prax.One;
import com.example.prax.Second;
import com.example.prax.Third;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;

public class StepperAdapter extends AbstractFragmentStepAdapter {
    public StepperAdapter(@NonNull FragmentManager fm, @NonNull Context context) {
        super(fm, context);
    }

    @Override
    public Step createStep(int position) {

        switch (position) {
           case 0:
                return new One();
            case 1:
                return new Second();
            case 2:
                return new Third();
            case 3:
                return new Four();
            case 4:
                return new Five();
        }
        return new One();

    }


    @Override
    public int getCount() {
        return 5;
    }
}
