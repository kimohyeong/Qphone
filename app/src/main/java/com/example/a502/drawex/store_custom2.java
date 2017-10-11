package com.example.a502.drawex;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class store_custom2 extends AppCompatActivity {
    CouponView couponView;
    CouponMaker maker;
    Bundle bundle;
    int height;
    int width;

    TextView stamp;
    TextView rowTv;
    int stampNum=10;
    int rowNum=5;
    boolean vertical=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_custom2);

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
        maker.setNumOfRow(rowNum);
        maker.setNumOfStamp(stampNum);
        couponView.setMaker(maker);

        ///////////////////////////
        maker.setUserStamp(3);
        ///////////////////////////

        //stampNum 설정
        stamp=(TextView)findViewById(R.id.stampNum);
        rowTv=(TextView)findViewById(R.id.rowNum);

        //seekBar 조절하기
        ((SeekBar)findViewById(R.id.sb_alpha)).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int alpha=seekBar.getProgress();
                Paint circlePaint=maker.getCirclePaint();
                circlePaint.setAlpha(alpha);
                maker.setCirclePaint(circlePaint);
                maker.execute();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //다음화면으로 넘김
        findViewById(R.id.btn_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),store_custom3.class);
                Bundle bundle=new Bundle();
                bundle.putBoolean("round",maker.getRound());
                bundle.putInt("color",maker.getCouponCol().getColor());
                bundle.putBoolean("photo",maker.getPhoto());
                bundle.putBoolean("vertical",vertical);
                if(maker.getPhoto())
                    bundle.putParcelable("bitmap",maker.getBackgroundImage());
                bundle.putInt("totalNum",maker.getNumOfStamp());
                bundle.putInt("rowNum",maker.getNumOfRow());
                bundle.putInt("alpha",maker.getCirclePaint().getAlpha());
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        height=couponView.getHeight();
        width=couponView.getWidth();
        if(vertical=bundle.getBoolean("vertical"))
        {
            ViewGroup.LayoutParams params=couponView.getLayoutParams();
            params.width=(int)(height*1.15);
            params.height=width;
            couponView.setLayoutParams(params);
        }
    }

    @Override
    public void onBackPressed() {

    }

    public void numberUpDown(View view)
    {
        switch (view.getId())
        {
            case R.id.btnUp:
                stampNum++;
                stamp.setText(stampNum+"");
                break;
            case R.id.btnDown:
                if(stampNum==1)
                {
                    Toast.makeText(this,"적어도 1 이상이어야 합니다.",Toast.LENGTH_SHORT).show();
                    return;
                }
                stampNum--;
                stamp.setText(stampNum+"");
                break;
            case R.id.btnRowUp:
                if(rowNum>=stampNum)
                {
                    Toast.makeText(this,"총 개수를 초과합니다.",Toast.LENGTH_SHORT).show();
                    return;
                }
                rowNum++;
                rowTv.setText(rowNum+"");
                break;
            case R.id.btnRowDown:
                if(rowNum==1)
                {
                    Toast.makeText(this,"적어도 1 이상이어야 합니다.",Toast.LENGTH_SHORT).show();
                    return;
                }
                rowNum--;
                rowTv.setText(rowNum+"");
                break;
        }
        maker.setNumOfStamp(stampNum);
        maker.setNumOfRow(rowNum);
        maker.execute();
    }
}
