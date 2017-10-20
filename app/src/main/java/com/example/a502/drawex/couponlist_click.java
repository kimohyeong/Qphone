package com.example.a502.drawex;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 502 on 2017-10-14.
 */

public class couponlist_click extends AppCompatActivity {
    private CouponView couponView;
    private CouponMaker maker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.couponlist_click);

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);

        Intent i =getIntent();
        couponView=(CouponView)findViewById(R.id.couponView);
        maker=new CouponMaker(couponView);
        couponView.setMaker(maker);

        setPresetting();
        if (i != null) {
            String storename = i.getStringExtra("storename");
            collapsingToolbar.setTitle(storename);

        }
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        //super.onWindowFocusChanged(hasFocus);
        int width = couponView.getWidth();
        int height = (int) (width * 2 / 3.0);
        ViewGroup.LayoutParams params = couponView.getLayoutParams();
        params.height = height;
        couponView.setLayoutParams(params);
    }

    public void setPresetting()
    {
        //이부분을 DB에서 받아온 정보로 적용해야한다. 여기는 가게 ID로 해당 가게의 쿠폰 정보 받아오기
        Paint temp;
        temp=maker.getBackPaint();
        temp.setColor(Color.parseColor("#fda69d"));
        maker.setBackPaint(temp);
        temp=maker.getInsidePaint();
        temp.setColor(Color.WHITE);
        maker.setInsidePaint(temp);
        temp=maker.getLinePaint();
        temp.setColor(Color.LTGRAY);
        maker.setLinePaint(temp);
        maker.setNumOfStamp(10);
        maker.setNumOfCol(5);


        /////////////이부분은 string형으로 받아온 이벤트 리스트를 boolean형으로 바꾸는 구간
        ///////////일단은 임의로 설정////////////////////////////////////////////
        String eventList="";
        for(int i=0;i<100;i++)
            eventList+='F';
        char[] arr=eventList.toCharArray();
        //arr[9]='T';
        //eventList=arr.toString();
        boolean[] events=new boolean[100];
        for(int i=0; i<events.length; i++)
        {
            if(eventList.charAt(i)=='T')
                events[i]=true;
            else
                events[i]=false;
        }
        /////////////////////////////////////////
        maker.setEvents(events);
        maker.setStr("10개를 모으면 아메리카노가 무료!");

        ///////////////////////유저로부터 받아올 정보///////////////////////////
        maker.setUserStamp(5);
        /////////////////////////////기본설정값/////////////////////////////////
        maker.setStampBefore(BitmapFactory.decodeResource(this.getResources(),R.drawable.default_stamp1));
        maker.setStampAfter(BitmapFactory.decodeResource(this.getResources(),R.drawable.default_stamp2));
        maker.setEventBefore(BitmapFactory.decodeResource(this.getResources(),R.drawable.default_event1));
        maker.setEventAfter(BitmapFactory.decodeResource(this.getResources(),R.drawable.default_event2));

    }
}
