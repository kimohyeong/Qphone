package com.example.a502.drawex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class store_custom3 extends AppCompatActivity {

    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_custom3);
        Intent intent=getIntent();
        bundle=intent.getExtras();
        //다음화면으로 넘김
        findViewById(R.id.btn_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),store_custom4.class);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });
    }
}
