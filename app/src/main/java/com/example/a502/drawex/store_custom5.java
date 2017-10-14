package com.example.a502.drawex;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class store_custom5 extends AppCompatActivity {
    CouponView couponView;
    CouponMaker maker;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_custom5);

        couponView=(CouponView)findViewById(R.id.couponView);
        maker=new CouponMaker(couponView);
        couponView.setMaker(maker);

        Intent intent=getIntent();
        bundle=intent.getExtras();

        setPresetting();

        findViewById(R.id.btn_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //데이터베이스에 값을 저장하는 부분.
                int back=bundle.getInt("backCol");
                int in=bundle.getInt("inCol");
                int line=bundle.getInt("lineCol");
                int stampNum=bundle.getInt("stampNum");
                int colNum=bundle.getInt("colNum");
                String str=bundle.getString("str");
                String events="";
                boolean[] eventArray=bundle.getBooleanArray("events");
                for(int i=0;i<eventArray.length;i++)
                {
                    if(eventArray[i])
                        events+="T";
                    else
                        events+="F";
                }

                ////////////여기에 쿼리 추가!!!!///////
                /*
                * Insert into coupon_image (shop_code, inside_color, background_color, line_color,
                * total_stamp, column_stamp, event_array, event_string) values
                  (shop_id, in, back, line,stampNum,colNum, events, str)
                * */
                finish();
            }
        });
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
        //이전 값 쿠폰에 적용
        Paint temp;
        temp=maker.getBackPaint();
        temp.setColor(bundle.getInt("backCol"));
        maker.setBackPaint(temp);
        temp=maker.getInsidePaint();
        temp.setColor(bundle.getInt("inCol"));
        maker.setInsidePaint(temp);
        temp=maker.getLinePaint();
        temp.setColor(bundle.getInt("lineCol"));
        maker.setLinePaint(temp);

        maker.setNumOfStamp(bundle.getInt("stampNum"));
        maker.setNumOfCol(bundle.getInt("colNum"));
        maker.setStampBefore(BitmapFactory.decodeResource(this.getResources(), R.drawable.default_stamp1));
        maker.setStampAfter(BitmapFactory.decodeResource(this.getResources(), R.drawable.default_stamp2));
        maker.setEventBefore(BitmapFactory.decodeResource(this.getResources(), R.drawable.default_event1));
        maker.setEventAfter(BitmapFactory.decodeResource(this.getResources(), R.drawable.default_event2));
        maker.setEvents(bundle.getBooleanArray("events"));
        maker.setStr(bundle.getString("str"));
    }
}
