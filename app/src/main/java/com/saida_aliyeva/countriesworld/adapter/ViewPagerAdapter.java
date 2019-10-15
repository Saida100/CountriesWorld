package com.saida_aliyeva.countriesworld.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.saida_aliyeva.countriesworld.fragment.CountriesFragment;
import com.saida_aliyeva.countriesworld.fragment.QuizFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new CountriesFragment();
            default:
                return new QuizFragment();

        }

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Countries";
            default:
                return "Quiz";

        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
