package com.example.armytrainingassistant.Controller;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.armytrainingassistant.Activities.MainActivity;
import com.example.armytrainingassistant.R;
import com.example.armytrainingassistant.View.M240Fragment;
import com.example.armytrainingassistant.View.QualFragment;
import com.example.armytrainingassistant.View.NewTraineeFragment;
import com.example.armytrainingassistant.View.ScoresFragment;
import com.example.armytrainingassistant.View.WeaponFragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    //tab titles now dynamic
    private static final int[] TAB_TITLES = new int[]{R.string.new_shooter, R.string.scores, R.string.qualification_tab, R.string.regulation_tab};
    private final Context mContext;
    private int fileID;

    public SectionsPagerAdapter(Context context, FragmentManager fm, int fileID) {
        super(fm);
        mContext = context;
        this.fileID = fileID;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        if(position == 0)
            return NewTraineeFragment.newInstance(position + 1);
        else if(position == 1)
            return ScoresFragment.newInstance(position + 1);
        else if (position == 2)
            return QualFragment.newInstance(position + 1);
        else if (position == 3){
            return WeaponFragment.newInstance(position + 1, fileID);

        }

        return null;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Get count based on number of tabs
        return TAB_TITLES.length;
    }
}