package com.example.a502.drawex;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by 502 on 2017-10-20.
 */

public class store_consumers extends Fragment {
    ViewGroup rootView;
    AppCompatActivity activity;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ViewGroup)inflater.inflate(R.layout.store_consumers, container, false);
        activity=(AppCompatActivity)getActivity();

        ListView listview ;
        store_consumers_adapter adapter;

        adapter= new store_consumers_adapter();


        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView)rootView.findViewById(R.id.consumers);
        listview.setAdapter(adapter);


        adapter.addItem("kdhdud15",1);
        adapter.addItem("goeun",0);
        adapter.addItem("soo",2);

        return rootView;
    }

}
