package com.example.a502.drawex;

import android.content.Intent;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.couponlist_click);

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);

        Intent i =getIntent();

        if (i != null) {
            String storename = i.getStringExtra("storename");
            collapsingToolbar.setTitle(storename);

        }
    }


}
