package com.example.a502.drawex;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

/**
 * Created by 502 on 2017-10-01.
 */

public class store_notice extends Fragment {
    ViewGroup rootView;
    AppCompatActivity activity;
    ImageView newNotice;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootView = (ViewGroup)inflater.inflate(R.layout.store_notice, container, false);
        activity = (AppCompatActivity)getActivity();

        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        ListView listview ;
        store_notice_Adapter adapter;

        // Adapter 생성
        adapter = new store_notice_Adapter() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView)rootView.findViewById(R.id.listVIew);
        listview.setAdapter(adapter);

        // 첫 번째 아이템 추가.
        adapter.addItem("꽥", "2017.10.01") ;
        // 두 번째 아이템 추가.
        adapter.addItem("힘들당...", "2017.10.02") ;
        // 세 번째 아이템 추가.
        adapter.addItem("흠", "2017.10.01") ;

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {

                store_notice_listviewItem item = (store_notice_listviewItem) parent.getItemAtPosition(position) ;
                Log.e("log1","click "+ position+ "  "+item.getTitle());

                store_notice_click sc=new store_notice_click();

                Bundle bundle = new Bundle();

                bundle.putString("key","showNotice");
                bundle.putString("title",item.getTitle());
                bundle.putString("content",item.getDesc());
                sc.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame, sc).commit();
            }
        }) ;

        newNotice = (ImageView)rootView.findViewById(R.id.addNoticeBtn);
        newNotice.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                store_notice_click sc=new store_notice_click();

                Bundle bundle2 = new Bundle();
                bundle2.putString("key","newNotice");
                sc.setArguments(bundle2);


                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame, sc).commit();



            }
        });

        return rootView;

    }
}
