package com.example.a502.drawex;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class store_custom1 extends AppCompatActivity {

    CouponView couponView;
    CouponMaker maker;
    ColorChoice choicer;
    RadioGroup directGroup;
    RadioGroup frameGroup;
    RadioGroup backGroup;

    ViewGroup.LayoutParams params;

    SeekBar[] rgbSeekBars;

    int height;
    int width;
    int[] ids;
    boolean vertical=false;

    /**/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_custom1);

        //쿠폰 뷰 설정
        couponView=(CouponView)findViewById(R.id.couponView);
        maker=new CouponMaker(couponView);
        choicer=new ColorChoice(this);
        couponView.setMaker(maker);

        //컬러 View 리스너 설정
        ids=new int[10];
        setIds(ids);
        viewClick click=new viewClick(ids,maker,this);
        for(int i=0; i<ids.length; i++)
            findViewById(ids[i]).setOnClickListener(click);

        //rgb Seek Bar 리스너 설정
        rgbSeekBars=new SeekBar[3];
        rgbSeekBars[0]=(SeekBar)findViewById(R.id.sb_r);
        rgbSeekBars[1]=(SeekBar)findViewById(R.id.sb_g);
        rgbSeekBars[2]=(SeekBar)findViewById(R.id.sb_b);

        for(int i=0; i<rgbSeekBars.length; i++)
            rgbSeekBars[i].setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    maker.setCouponCol(choicer.getColor(rgbSeekBars));
                    maker.execute();
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });


        //라디오버튼 이벤트리스너 설정
        params=couponView.getLayoutParams();
        directGroup=(RadioGroup)findViewById(R.id.directGroup);
        directGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(radioGroup.getCheckedRadioButtonId()==R.id.vertical)
                {
                    params.width=(int)(height*1.15);
                    params.height=width;
                    couponView.setLayoutParams(params);
                    vertical=true;
                }
                else
                {
                    ViewGroup.LayoutParams params=couponView.getLayoutParams();
                    params.width=width;
                    params.height=height;
                    couponView.setLayoutParams(params);
                    vertical=false;
                }
            }
        });
        frameGroup=(RadioGroup)findViewById(R.id.frameGroup);
        frameGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(radioGroup.getCheckedRadioButtonId()==R.id.roundFrame)
                    maker.setRound(true);
                else
                    maker.setRound(false);
                couponView.reDraw();
            }
        });
        backGroup=(RadioGroup)findViewById(R.id.backgroundGroup);
        backGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (radioGroup.getCheckedRadioButtonId())
                {
                    case R.id.primeColor:
                        maker.setPhoto(false);
                        findViewById(R.id.simpleColor).setVisibility(View.VISIBLE);
                        findViewById(R.id.userChoice).setVisibility(View.GONE);
                        findViewById(R.id.pictureChoice).setVisibility(View.GONE);
                        break;
                    case R.id.colorChoice:
                        maker.setPhoto(false);
                        findViewById(R.id.simpleColor).setVisibility(View.GONE);
                        findViewById(R.id.userChoice).setVisibility(View.VISIBLE);
                        findViewById(R.id.pictureChoice).setVisibility(View.GONE);
                        break;
                    case R.id.picture:
                        findViewById(R.id.simpleColor).setVisibility(View.GONE);
                        findViewById(R.id.userChoice).setVisibility(View.GONE);
                        findViewById(R.id.pictureChoice).setVisibility(View.VISIBLE);

                        break;
                }
            }
        });

        //갤러리에서 이미지 찾기
        ((Button)findViewById(R.id.findPath)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,100);
            }
        });

        //next 버튼(Activity 전환)
        findViewById(R.id.btn_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(),store_custom2.class);
                Bundle bundle =new Bundle();
                bundle.putBoolean("round",maker.getRound());
                bundle.putInt("color",maker.getCouponCol().getColor());
                bundle.putBoolean("photo",maker.getPhoto());
                bundle.putBoolean("vertical",vertical);
                if(maker.getPhoto())
                    bundle.putParcelable("bitmap",maker.getBackgroundImage());
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
    }

    public void setIds(int[] ids)
    {
        ids[0]=R.id.blueView;   ids[1]=R.id.brownView;
        ids[2]=R.id.greenView;  ids[3]=R.id.mintView;
        ids[4]=R.id.redView;    ids[5]=R.id.violetView;
        ids[6]=R.id.orangeView; ids[7]=R.id.peachView;
        ids[8]=R.id.pinkView;   ids[9]=R.id.yellowView;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==100)
        {
            if(resultCode== Activity.RESULT_OK)
            {
                try{
                    ((TextView)findViewById(R.id.tv_path)).setText("경로: "+getImageNameToUri(data.getData()));
                    Bitmap img_bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),data.getData());
                    maker.setBackgroundImage(img_bitmap);
                    maker.setPhoto(true);
                }
                catch (Exception e)
                {
                    maker.setPhoto(false);
                    Toast.makeText(this,"오류가 발생하였습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
    public String getImageNameToUri(Uri data)
    {
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(data, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        cursor.moveToFirst();

        String imgPath = cursor.getString(column_index);
        String imgName = imgPath.substring(imgPath.lastIndexOf("/")+1);

        return imgName;
    }
}
