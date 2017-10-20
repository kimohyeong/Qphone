package com.example.a502.drawex;


import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

/**
 * Created by 502 on 2017-10-20.
 */

public class store_consumers_item_click extends Fragment {

    ViewGroup rootView;
    AppCompatActivity activity;
    CouponView couponView;
    CouponMaker maker;
    TextView idTextView;
    TextView numTextView;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.store_consumers_item_click, container, false);
        activity=(AppCompatActivity)getActivity();
        couponView=(CouponView)rootView.findViewById(R.id.couponView);
        maker=new CouponMaker(couponView);
        couponView.setMaker(maker);
        idTextView=(TextView)rootView.findViewById(R.id.idText);
        numTextView=(TextView)rootView.findViewById(R.id.hasCouponNum) ;

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String id=bundle.getString("id");
            int hascouponNum = bundle.getInt("num");
            idTextView.setText(id);
            numTextView.setText(hascouponNum + "");
        }

            ViewTreeObserver vto = rootView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {

                int width = couponView.getWidth();
                int height = (int) (width * 2 / 3.0);
                ViewGroup.LayoutParams params = couponView.getLayoutParams();
                params.height = height;
                couponView.setLayoutParams(params);
                Log.e("log1","height : "+ height);
            }
        });



        setPresetting();

        return rootView;
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
