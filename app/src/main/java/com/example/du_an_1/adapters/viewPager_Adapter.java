package com.example.du_an_1.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.du_an_1.Fragment.Fragment_quanLy_DHD;
import com.example.du_an_1.Fragment.Fragment_quanLy_NDH;

public class viewPager_Adapter extends FragmentStateAdapter {
    public viewPager_Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = null ;
        switch (position){
            case 0:
                fragment = Fragment_quanLy_DHD.newInstance();
                break;
            case 1:
                fragment = Fragment_quanLy_NDH.newInstance();
                break;
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
