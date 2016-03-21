package com.puzzle.appname.cake;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class SwipeViewAdapter extends FragmentPagerAdapter {
    private int fragmentCount = 0;
    private ArrayList<Fragment> fragments = new ArrayList<Fragment>();

    public SwipeViewAdapter(FragmentManager fm) {
        super(fm);
    }


    public void addFragment(Fragment f) {
        fragments.add(f);
        ++fragmentCount;
    }

    public boolean removeFragments(Fragment fragment) {
        try {
            if (fragments.remove(fragment)) {
                --fragmentCount;
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public boolean removeFragments(int fragment) {
        try {
            fragments.remove(fragment);
            --fragmentCount;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Fragment getItem(int position) {
        try {
            return fragments.get(position);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int getCount() {
        return fragmentCount;
    }

}
