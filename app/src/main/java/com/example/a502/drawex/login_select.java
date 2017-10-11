package com.example.a502.drawex;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by 502 on 2017-10-11.
 */

public class login_select extends Fragment {
    ViewGroup rootView;
    AppCompatActivity activity;

    Button memberBtn;
    Button storeBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootView = (ViewGroup)inflater.inflate(R.layout.login_select, container, false);

        activity = (AppCompatActivity) getActivity();

        memberBtn=(Button)rootView.findViewById(R.id.memberCheck);
        storeBtn=(Button)rootView.findViewById(R.id.storeCheck);

        memberBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(activity, "회원", Toast.LENGTH_LONG).show();
                login l =new login();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame, l).commit();


            }
        });
        storeBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(activity, "가게", Toast.LENGTH_LONG).show();
                login l2 =new login();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame, l2).commit();


            }
        });
        return rootView;


    }




}
