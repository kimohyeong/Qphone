package com.example.a502.drawex;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by 502 on 2017-09-29.
 */

public class register extends Fragment{

    ViewGroup rootView;

    AppCompatActivity activity;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ViewGroup)inflater.inflate(R.layout.register, container, false);

        activity = (AppCompatActivity) getActivity();

        //번들로 넘어오는부분 ★★★★★★★★★★저어어엉고오오오으으으은 이부분임!!!!!
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            int i = bundle.getInt("check");
            if(i==1)
                Toast.makeText(activity, "일반 회원가입", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(activity, "가게회원가입", Toast.LENGTH_LONG).show();
        }



        return rootView;
    }
}
