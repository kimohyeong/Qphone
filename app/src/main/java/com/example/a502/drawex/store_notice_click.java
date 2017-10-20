package com.example.a502.drawex;


import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by 502 on 2017-10-02.
 */

public class store_notice_click extends Fragment {
    EditText titleEdit;
    EditText contentEdit;
    TextView titleText;
    TextView contentText;
    ViewGroup rootView;

    AppCompatActivity activity;
    Bundle bundle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ViewGroup)inflater.inflate(R.layout.store_notice_click, container, false);

        titleEdit= rootView.findViewById(R.id.titleEdit);
        contentEdit=rootView.findViewById(R.id.contentEdit);
        titleText=rootView.findViewById(R.id.titleText);
        contentText=rootView.findViewById(R.id.contentText);

        activity=(AppCompatActivity)getActivity();
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        bundle = this.getArguments();
        if(bundle!= null){
            Log.e("log1","notNull");
            String value= bundle.getString("key");
            if(value.equals("newNotice")){
                Log.e("log1","newNotice");
                titleEdit.setVisibility(View.VISIBLE);
                contentEdit.setVisibility(View.VISIBLE);

                titleText.setVisibility(View.INVISIBLE);
                contentText.setVisibility(View.INVISIBLE);

                Button btn = rootView.findViewById(R.id.editBtn);
                btn.setText("등록");

                btn=rootView.findViewById(R.id.delBtn);
                btn.setVisibility(View.INVISIBLE);
            }
            else if(value.equals("showNotice")){
                Log.e("log1","showNotice");
                titleEdit.setVisibility(View.INVISIBLE);
                contentEdit.setVisibility(View.INVISIBLE);

                titleText.setVisibility(View.VISIBLE);
                titleText.setText(bundle.getString("title"));
                contentText.setVisibility(View.VISIBLE);
                contentText.setText(bundle.getString("content"));

            }
        }
        return rootView;
    }

}
