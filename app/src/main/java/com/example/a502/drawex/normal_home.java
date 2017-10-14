package com.example.a502.drawex;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

/**
 * Created by 502 on 2017-09-29.
 */

public class normal_home extends Fragment {
    ViewGroup rootView;
    AppCompatActivity activity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ViewGroup)inflater.inflate(R.layout.normal_home, container, false);
        activity=(AppCompatActivity)getActivity();

        ListView listview ;
        //couponlist_adpater adapter;

        // Adapter 생성
        //adapter = new couponlist_adpater() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView)rootView.findViewById(R.id.couponList);
        //listview.setAdapter(adapter);

        //adapter.addItem("베르데",2,10);
        //adapter.addItem("베니스의 상인", 4,14);
       // adapter.addItem("크리스마스자몽", 3, 5);

        return rootView;
    }


}
