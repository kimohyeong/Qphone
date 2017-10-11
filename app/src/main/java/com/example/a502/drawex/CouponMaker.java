package com.example.a502.drawex;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by soo on 2017-09-23.
 */

public class CouponMaker {
    private boolean round=true;
    private Paint couponCol;
    private Paint circlePaint;
    private CouponView mCouponView;
    private boolean photo;
    private Bitmap backgroundImage;
    private Bitmap stampBefore;
    private Bitmap stampAfter;
    private int numOfStamp=0;
    private int numOfRow=1;
    private int userStamp=0;

    public CouponMaker(CouponView couponView)
    {
        couponCol=new Paint();
        couponCol.setColor(Color.GRAY);
        couponCol.setStyle(Paint.Style.FILL);
        circlePaint=new Paint();
        circlePaint.setColor(Color.WHITE);
        mCouponView=couponView;
        photo=false;
    }

    public void setRound(Boolean round){this.round=round;}
    public boolean getRound(){return round;}
    public void setCouponCol(int color) {couponCol.setColor(color);}
    public Paint getCouponCol(){return couponCol;}
    public void execute(){mCouponView.reDraw();}
    public void setBackgroundImage(Bitmap backgroundImage) {this.backgroundImage = backgroundImage;}
    public Bitmap getBackgroundImage() {return backgroundImage;}
    public void setPhoto(boolean photo) {this.photo = photo;}
    public boolean getPhoto(){return photo;}
    public void setNumOfStamp(int numOfStamp) {this.numOfStamp = numOfStamp;}
    public int getNumOfStamp(){return numOfStamp;}
    public void setNumOfRow(int numOfRow) {this.numOfRow = numOfRow;}
    public int getNumOfRow(){return this.numOfRow;}
    public void setStampBefore(Bitmap stampBefore) {this.stampBefore = stampBefore;}
    public Bitmap getStampBefore() {return stampBefore;}
    public void setStampAfter(Bitmap stampAfter) {this.stampAfter = stampAfter;}
    public Bitmap getStampAfter() {return stampAfter;}
    public void setUserStamp(int userStamp) {
        this.userStamp = userStamp;
    }
    public int getUserStamp() {
        return userStamp;
    }
    public Paint getCirclePaint() {return circlePaint;}
    public void setCirclePaint(Paint circlePaint) {this.circlePaint = circlePaint;}
}
