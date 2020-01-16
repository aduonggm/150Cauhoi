package com.duongnb.a150lythuyetxemay.Base;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    ArrayList<String> arrTitle = new ArrayList<>();
    ArrayList<Fragment> arrFragmnet = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    public void addFragmnet(Fragment fragment , String title){
        arrFragmnet.add(fragment);
        arrTitle.add(title);
    }

    @Override
    public Fragment getItem(int position) {
        return arrFragmnet.get(position);
    }

    @Override
    public int getCount() {
        return arrFragmnet.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return arrTitle.get(position);
    }
}
