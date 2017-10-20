package com.example.a502.drawex;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
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

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                store_consumers_item item = (store_consumers_item) parent.getItemAtPosition(position) ;

                String consumerId = item.getId() ;
                int hasCouponNum = item.getHascouponNum() ;

                Bundle bundle = new Bundle();
                bundle.putString("id",consumerId);
                bundle.putInt("num",hasCouponNum);

                store_consumers_item_click scc=new store_consumers_item_click();
                scc.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame, scc).commit();
            }
        }) ;



        return rootView;
    }

}
