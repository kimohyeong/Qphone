package com.example.a502.drawex;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by 502 on 2017-10-20.
 */

public class normal_notice_click extends AppCompatActivity {


    TextView titleText;
    TextView contentText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.normal_notice_click);


        titleText=(TextView)findViewById(R.id.titleText);
        contentText=(TextView)findViewById(R.id.contentText);

        Intent i =getIntent();

        String title=i.getStringExtra("title");
        String content=i.getStringExtra("content");

        titleText.setText(title);
        contentText.setText(content);


    }
}
