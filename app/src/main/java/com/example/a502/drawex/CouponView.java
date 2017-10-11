package com.example.a502.drawex;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by soo on 2017-09-23.
 */

public class CouponView extends View {
    private TypedArray types;

    private CouponMaker maker;

    public CouponView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        types=context.obtainStyledAttributes(attrs,R.styleable.CouponView);
    }

    public void reDraw() { invalidate(); }
    public void setMaker(CouponMaker couponMaker){maker=couponMaker;}
    public CouponMaker getMaker(){return maker;}

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int height=getHeight();
        int width=getWidth();
        Paint couponCol=maker.getCouponCol();

        //배경 그리기
        if(maker.getPhoto())
        {
            Bitmap b = maker.getBackgroundImage();
            Bitmap resize_bitmap = Bitmap.createScaledBitmap(b, width, height, true);
            canvas.drawBitmap(resize_bitmap, 0, 0, couponCol);
        }
        else
        {
            if(maker.getRound())
                canvas.drawRoundRect(0,0,width,height,50,50,couponCol);
            else
                canvas.drawRect(0,0,width,height,couponCol);
        }

        //도장칸 그리기
        Paint textPaint=new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        textPaint.setTextSize(56);
        int widthUnitSize=width/maker.getNumOfRow();
        int rowNum=maker.getNumOfStamp()/maker.getNumOfRow();
        if(maker.getNumOfStamp()%maker.getNumOfRow()>0)
            rowNum++;
        int heightUnitSize=0;
        if(rowNum!=0)
            heightUnitSize=height/rowNum;

        int halfWidth=widthUnitSize/2;
        int halfHeight=heightUnitSize/2;

        //백그라운드 원 그리기
        Paint circlePaint=maker.getCirclePaint();
        int rad= (halfHeight>halfWidth)?halfWidth-15:halfHeight-15;
        int cx;
        int cy;
        Bitmap stampBefore=null;
        Bitmap stampAfter=null;

        if(maker.getNumOfStamp()>0)
        {
            stampBefore=Bitmap.createScaledBitmap(maker.getStampBefore(), rad*2, rad*2, true);
            stampAfter=Bitmap.createScaledBitmap(maker.getStampAfter(), rad*2, rad*2, true);
        }


        for(int i=0; i<maker.getNumOfStamp(); i++)
        {
            for(int j=0; j<rowNum; j++)
            {
                cx=widthUnitSize*(i-j*maker.getNumOfRow())+halfWidth;
                cy=heightUnitSize*j+halfHeight;
                canvas.drawCircle(cx,cy,rad,circlePaint);
                canvas.drawBitmap(stampBefore,cx-rad,cy-rad,circlePaint);
            }
        }

        for(int i=0; i<maker.getUserStamp();i++)
        {
            cx=widthUnitSize*(i%maker.getNumOfRow())+halfWidth;
            cy=heightUnitSize*(i/maker.getNumOfRow())+halfHeight;
            canvas.drawBitmap(stampAfter,cx-rad,cy-rad,circlePaint);
        }
    }
}
