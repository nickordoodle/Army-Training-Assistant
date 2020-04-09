package com.example.armytrainingassistant.Controller;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.armytrainingassistant.R;
import com.example.armytrainingassistant.View.M240Fragment;
import com.example.armytrainingassistant.View.M249Fragment;
import com.example.armytrainingassistant.View.NewTraineeFragment;
import com.example.armytrainingassistant.View.ScoresFragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.new_shooter, R.string.scores, R.string.m240_reg, R.string.m249_reg};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).

        if(position == 0)
            return NewTraineeFragment.newInstance(position + 1);
        else if(position == 1)
            return ScoresFragment.newInstance(position + 1);
        else if (position == 2)
            return M240Fragment.newInstance(position + 1);
        else if (position == 3)
            return M249Fragment.newInstance(position + 1);

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