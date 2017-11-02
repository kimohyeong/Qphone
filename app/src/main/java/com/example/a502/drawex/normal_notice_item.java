package com.example.a502.drawex;

import android.graphics.drawable.Drawable;

/**
 * Created by 502 on 2017-11-02.
 */

public class normal_notice_item {
    private String titleStr ;
    private String dateStr ;


    public void setTitle(String title) {
        titleStr = title ;
    }
    public void setDate(String desc) {
        dateStr = desc ;
    }

    public String getTitle() {
        return this.titleStr ;
    }
    public String getDate() {
        return this.dateStr ;
    }


}
