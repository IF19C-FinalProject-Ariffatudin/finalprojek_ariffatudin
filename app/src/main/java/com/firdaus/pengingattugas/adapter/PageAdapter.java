package com.firdaus.pengingattugas.adapter;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.firdaus.pengingattugas.fragment.JumatFragment;
import com.firdaus.pengingattugas.fragment.KamisFragment;
import com.firdaus.pengingattugas.fragment.RabuFragment;
import com.firdaus.pengingattugas.fragment.SelasaFragment;
import com.firdaus.pengingattugas.fragment.SeninFragment;

public class PageAdapter extends FragmentPagerAdapter {

    private int numOfTabs;

    public PageAdapter(FragmentManager fm, int numOfTabs){
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                SeninFragment tab1 = new SeninFragment();
                return tab1;
            case 1:
                SelasaFragment tab2 = new SelasaFragment();
                return tab2;
            case 2:
                RabuFragment tab3 = new RabuFragment();
                return tab3;
            case 3:
                KamisFragment tab4 = new KamisFragment();
                return tab4;
            case 4:
                JumatFragment tab5 = new JumatFragment();
                return tab5;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
