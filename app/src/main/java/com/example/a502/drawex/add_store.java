package com.example.a502.drawex;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by 502 on 2017-11-02.
 */

public class add_store extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_store);

        ListView listview ;
        add_store_adapter adapter;

        // Adapter 생성
        adapter = new add_store_adapter() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listVIew);
        listview.setAdapter(adapter);

        // 첫 번째 아이템 추가.
        adapter.addItem("베르데", "광운대학교 정문 ");
        // 두 번째 아이템 추가.
        adapter.addItem("베니스", "광운대학교 후문");
        // 세 번째 아이템 추가.
        adapter.addItem("크자", "노원구 광운로 20길");

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                add_store_item item = (add_store_item)parent.getItemAtPosition(position);

                String titleStr = item.getTitle();
                String poscStr = item.getPos();

                Toast toast = Toast.makeText(getApplicationContext(), titleStr+"을 추가하였습니다.", Toast.LENGTH_LONG);
                toast.show();

            }
        }) ;

    }


}
