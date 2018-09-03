package com.example.zjubme.teethmanagement;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MessageListFragmentPagerAdepter extends FragmentPagerAdapter {
    private String[] mTitles = new String[]{"对话消息"};

    public MessageListFragmentPagerAdepter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new MessageListFragment();
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
