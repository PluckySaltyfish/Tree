package com.example.plucky.mytree.fragment.profile;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.plucky.mytree.fragment.achievement.achievement_fragment;
import com.example.plucky.mytree.fragment.task.TaskFragment;
import com.example.plucky.mytree.fragment.tree.TreeFragment;


public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private String[] mTitles = new String[]{" ", " ", " "," "};

    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 1) {
            return new ProfileFragment();
        } else if (position == 2) {
            return new TaskFragment();
        }else if (position==3){
            return new achievement_fragment();
        }
        return new TreeFragment();
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}


