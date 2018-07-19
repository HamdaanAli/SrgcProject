package com.mcntraining.demo2.Fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mcntraining.demo2.Fragments.EducationLoanFragment;
import com.mcntraining.demo2.Fragments.HostelFragment;
import com.mcntraining.demo2.Fragments.LibraryFragment;

/**
 * Created by user on 4/1/2018.
 */

class PagerViewAdapter extends FragmentPagerAdapter {
    public PagerViewAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                HostelFragment hostelFragment=new HostelFragment();
                return hostelFragment;
            case 1:
                LibraryFragment libraryFragment=new LibraryFragment();
                return libraryFragment;

            case 2:
                EducationLoanFragment educationLoanFragment=new EducationLoanFragment();
                return educationLoanFragment;

            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 3;
    }
}
