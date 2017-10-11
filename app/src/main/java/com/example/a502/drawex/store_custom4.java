package com.example.a502.drawex;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class store_custom4 extends AppCompatActivity {
    CouponView couponView;
    CouponMaker maker;
    Bundle bundle;
    int height;
    int width;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_custom4);

        Intent intent= getIntent();
        bundle=intent.getExtras();

        //쿠폰 뷰 설정
        couponView=(CouponView)findViewById(R.id.couponView);
        maker=new CouponMaker(couponView);
        maker.setRound(bundle.getBoolean("round"));
        maker.setCouponCol(bundle.getInt("color"));
        maker.setPhoto(bundle.getBoolean("photo"));
        maker.setStampBefore(BitmapFactory.decodeResource(this.getResources(), R.drawable.default_stamp1));
        maker.setStampAfter(BitmapFactory.decodeResource(this.getResources(),R.drawable.default_stamp2));
        if(maker.getPhoto())
            maker.setBackgroundImage((Bitmap) bundle.getParcelable("bitmap"));
        maker.setNumOfRow(bundle.getInt("rowNum"));
        maker.setNumOfStamp(bundle.getInt("totalNum"));
        Paint circlePaint=maker.getCirclePaint();
        circlePaint.setAlpha(bundle.getInt("alpha"));
        maker.setCirclePaint(circlePaint);
        couponView.setMaker(maker);

        //yes 버튼 데이터베이스에 쿼리날림
        /*쿼리에 들어갈 내용
        * 1. 가게코드
        * 2. boolean형 모서리 round인지 아닌지
        * 3. boolean형 사진/컬러
        * 4. int형 컬러 값
        * 5. 만약 사진이라면 사진. 근데 사진 크기에도 영향을 받아서 사진 그냥 뺄까 생각중..
        * 6. int형 총 도장 갯수
        * 7. int형 한 행의 도장 갯수
        * 8. int형 도장 칸의 투명도
        * 9. int형 이벤트... 혹은 String형으로 저장할 예정*/
        findViewById(R.id.btn_yes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Yes 버튼누름 쿼리날림.",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Cancel",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        height=couponView.getHeight();
        width=couponView.getWidth();
        if(bundle.getBoolean("vertical"))
        {
            ViewGroup.LayoutParams params=couponView.getLayoutParams();
            params.width=(int)(height*1.15);
            params.height=width;
            couponView.setLayoutParams(params);
        }
    }
}
