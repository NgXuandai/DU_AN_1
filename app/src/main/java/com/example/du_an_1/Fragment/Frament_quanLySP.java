package com.example.du_an_1.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.du_an_1.R;
import com.example.du_an_1.adapter.Adapter_tab_sp;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

/**
 * A simple {@link Fragment} subclass.

 * create an instance of this fragment.
 */
public class Frament_quanLySP extends Fragment {

    ViewPager2 pager2;
    TabLayout tabLayout;
    TabLayoutMediator mediator;
    Adapter_tab_sp adapter_tab_sp;

    public Frament_quanLySP() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frament_quan_ly_s_p, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter_tab_sp = new Adapter_tab_sp(this);
        pager2 = view.findViewById(R.id.viewPage2_sp);
        pager2.setAdapter(adapter_tab_sp);
        tabLayout = view.findViewById(R.id.tabLayout_sp);
        mediator = new TabLayoutMediator(tabLayout, pager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if (position == 0) {
                    tab.setText("Are Trading");
                } else {
                    tab.setText("Stop Business");
                }
            }
        });

        mediator.attach();
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                adapter_tab_sp.loadData();
                if (tab.getPosition() == 0) {
                    FragmentManager manager = getActivity().getSupportFragmentManager();
                    manager.beginTransaction().replace(R.id.framelayout, new Frag_load()).commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    public TabLayout getTabLayout() {
        return tabLayout;
    }
}