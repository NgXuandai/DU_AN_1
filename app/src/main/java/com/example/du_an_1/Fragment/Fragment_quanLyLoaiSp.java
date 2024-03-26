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
import com.example.du_an_1.adapter.Adapter_tab_lsp;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_quanLyLoaiSp#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_quanLyLoaiSp extends Fragment {
    ViewPager2 pager2l;
    TabLayout tabLayoutl;
    TabLayoutMediator mediator;
    Adapter_tab_lsp adapter_tab_lsp;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_quanLyLoaiSp() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_quanLyLoaiSp.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_quanLyLoaiSp newInstance(String param1, String param2) {
        Fragment_quanLyLoaiSp fragment = new Fragment_quanLyLoaiSp();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_quan_ly_loai_sp, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter_tab_lsp = new Adapter_tab_lsp(this);
        pager2l = view.findViewById(R.id.viewPage2_lsp);
        pager2l.setAdapter(adapter_tab_lsp);
        tabLayoutl = view.findViewById(R.id.tabLayout_lsp);
        mediator = new TabLayoutMediator(tabLayoutl, pager2l, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if (position == 0) {
                    tab.setText("Đang Kinh Doanh");
                } else {
                    tab.setText("Ngừng Kinh Doanh");
                }
            }
        });

        mediator.attach();
}
}