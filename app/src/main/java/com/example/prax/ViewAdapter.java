package com.example.prax;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;


public class ViewAdapter extends FragmentStatePagerAdapter {
    ArrayList<Fragment>arrayList = new ArrayList<>();
    ArrayList<String>name = new ArrayList<>();
    public ViewAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return arrayList.get(position);
    }

    public void addFragment(Fragment fragment,String name1){
        arrayList.add(fragment);
        name.add(name1);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    public CharSequence getPageTitle(int position){
        return name.get(position);
    }


}
