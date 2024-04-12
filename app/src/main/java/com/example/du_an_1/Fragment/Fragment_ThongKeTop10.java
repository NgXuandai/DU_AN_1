package com.example.du_an_1.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_1.DAO.ThongkeDao;
import com.example.du_an_1.R;
import com.example.du_an_1.adapter.Top10Adapter;
import com.example.du_an_1.model.Food;

import java.util.ArrayList;

public class Fragment_ThongKeTop10 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thongketop10,container,false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_top10);
        ThongkeDao thongkeDao = new ThongkeDao(getContext());
        ArrayList<Food> list = thongkeDao.getTop10();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        Top10Adapter adapter = new Top10Adapter(getContext(),list);
        recyclerView.setAdapter(adapter);

        return view;

    }
}
