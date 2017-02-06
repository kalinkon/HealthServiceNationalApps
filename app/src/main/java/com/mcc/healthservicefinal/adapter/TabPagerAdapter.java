package com.mcc.healthservicefinal.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.widget.TextView;
import android.view.LayoutInflater;
import com.mcc.healthservicefinal.R;
import com.mcc.healthservicefinal.activity.tabFragment.TabFragmentPrevention;
import com.mcc.healthservicefinal.activity.tabFragment.TabFragmentSymptoms;
import com.mcc.healthservicefinal.objects.Content;

import java.util.ArrayList;
/**
 * Created by LINKON on 1/31/2017.
 */

/**
 * Created by LINKON on 1/30/2017.
 */



public class TabPagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    private ArrayList<Content> symptoms;
    private ArrayList<Content> preventions;
    private Context mContext;



    public TabPagerAdapter(FragmentManager fm, int NumOfTabs, ArrayList<Content> symptoms, ArrayList<Content> preventions, Context mContext) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.symptoms=symptoms;
        this.preventions=preventions;
        this.mContext=mContext;

    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                TabFragmentSymptoms tabFragmentSymptoms = new TabFragmentSymptoms();
                tabFragmentSymptoms.setSymtoms(symptoms,mContext);

                return tabFragmentSymptoms;
            case 1:
                TabFragmentPrevention tabFragmentPrevention = new TabFragmentPrevention();
                tabFragmentPrevention.setPreventions(preventions,mContext);
                return tabFragmentPrevention;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}