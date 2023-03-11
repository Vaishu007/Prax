package com.example.prax.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class Viewpageadpter extends FragmentStatePagerAdapter {
    ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    ArrayList<String> name = new ArrayList<>();

    public Viewpageadpter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    public void addfragment(Fragment fragment, String name1) {
        fragmentArrayList.add(fragment);
        name.add(name1);
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }


    public CharSequence getPageTitle(int position){return name.get(position);}



}
