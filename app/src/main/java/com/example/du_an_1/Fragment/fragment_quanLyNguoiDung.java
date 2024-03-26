package com.example.du_an_1.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.du_an_1.R;
import com.example.du_an_1.adapters.viewPager_Adapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class fragment_quanLyNguoiDung extends Fragment {

    TabLayout tabLayout ;
    ViewPager2 viewPager2 ;
    viewPager_Adapter viewPagerAdapter;
    public fragment_quanLyNguoiDung() {
        // Required empty public constructor
    }

    public static fragment_quanLyNguoiDung newInstance() {
        fragment_quanLyNguoiDung fragment = new fragment_quanLyNguoiDung();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quan_ly_nguoi_dung, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tabLayout = view.findViewById(R.id.id_tabLayout);
        viewPager2 = view.findViewById(R.id.id_viewPager);

        viewPagerAdapter = new viewPager_Adapter(getActivity());
        viewPager2.setAdapter(viewPagerAdapter);

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int i) {
                switch (i){
                    case 0:
                        tab.setText("Đang hoạt động");
                        break;
                    case 1:
                        tab.setText("Ngừng hoạt động");
                        break;
                }
            }
        });
        tabLayoutMediator.attach();
    }
}