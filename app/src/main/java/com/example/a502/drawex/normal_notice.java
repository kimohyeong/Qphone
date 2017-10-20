package com.example.a502.drawex;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by 502 on 2017-10-20.
 */

public class normal_notice extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.normal_notice);

        ListView listview ;
        store_notice_Adapter adapter;

        // Adapter 생성
        adapter = new store_notice_Adapter() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listVIew);
        listview.setAdapter(adapter);

        // 첫 번째 아이템 추가.
        adapter.addItem("gaegeaw", "2017.10.01") ;
        // 두 번째 아이템 추가.
        adapter.addItem("eagawegwg...", "2017.10.02") ;
        // 세 번째 아이템 추가.
        adapter.addItem("aegwewge흠", "2017.10.01") ;

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {

                store_notice_listviewItem item = (store_notice_listviewItem) parent.getItemAtPosition(position) ;


                store_notice_click sc=new store_notice_click();

                Intent i=new Intent(getApplicationContext(), normal_notice_click.class);
                i.putExtra("title",item.getTitle());
                i.putExtra("content",item.getDesc());

                startActivity(i);
            }
        }) ;

    }
}
