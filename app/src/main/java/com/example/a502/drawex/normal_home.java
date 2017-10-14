package com.example.a502.drawex;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

/**
 * Created by 502 on 2017-09-29.
 */

public class normal_home extends Fragment {
    ViewGroup rootView;
    AppCompatActivity activity;
    Spinner listormapSp;
    Spinner sortSp;
    ListView listview;
    couponlist_adpater adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ViewGroup)inflater.inflate(R.layout.normal_home, container, false);
        activity=(AppCompatActivity)getActivity();

        listormapSp=(Spinner)rootView.findViewById(R.id.listOrmap);
        sortSp=(Spinner)rootView.findViewById(R.id.sort);

        // Adapter 생성
        adapter = new couponlist_adpater() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView)rootView.findViewById(R.id.couponList);
        listview.setAdapter(adapter);

        adapter.addItem("베르데",2,10);
        adapter.addItem("베니스의 상인", 4,14);
        adapter.addItem("크리스마스자몽", 3, 5);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                couponlist_listviewitem item = (couponlist_listviewitem) parent.getItemAtPosition(position) ;
                Log.e("log1","click "+ position+ "  "+item.getStore_name());

                Intent i =new Intent(activity, couponlist_click.class);
                i.putExtra("storename",item.getStore_name());

                startActivity(i);
            }
        }) ;


        ArrayAdapter s = ArrayAdapter.createFromResource(activity, R.array.list, android.R.layout.simple_spinner_dropdown_item);
        listormapSp.setAdapter(s);

        listormapSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(activity ,"position : " + position + parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        ArrayAdapter s2 = ArrayAdapter.createFromResource(activity, R.array.sort, android.R.layout.simple_spinner_dropdown_item);
        sortSp.setAdapter(s2);

        sortSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(activity ,"position : " + position + parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        return rootView;
    }


}
