package com.journaldev.passingdatabetweenfragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by anupamchugh on 06/06/17.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0) {
            fragment = new fragmentGenerate();
        } else if (position == 1) {
            fragment = new fragmentChecker();
        } else if (position == 2) {
            fragment = new fragmentEncrypt_Decrypt();
        } else if (position == 3) {
            fragment = new fragmentInformation();
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0) {
            title = "Generate Password";
        } else if (position == 1) {
            title = "Strength Checker";
        } else if (position == 2) {
            title = "Encrypt Decrypt";
        } else if (position == 3) {
            title = "Account Setup";
        }
        return title;
    }
}
