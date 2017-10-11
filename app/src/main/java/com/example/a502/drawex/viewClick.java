package com.example.a502.drawex;

import android.content.Context;
import android.view.View;

/**
 * Created by soo on 2017-09-25.
 */

public class viewClick implements View.OnClickListener{
    int[] ids;
    CouponMaker couponMaker;
    Context mContext;
    ColorChoice choicer;

    public viewClick(int[] ids, CouponMaker maker, Context context)
    {
        this.ids=ids;
        couponMaker=maker;
        mContext=context;
        choicer=new ColorChoice(mContext);
    }
    @Override
    public void onClick(View view) {
        for(int i=0; i<ids.length; i++)
        {
            if(view.getId()==ids[i])
            {
                couponMaker.setCouponCol(choicer.getColor(i));
                couponMaker.execute();
                break;
            }
        }
    }
}
