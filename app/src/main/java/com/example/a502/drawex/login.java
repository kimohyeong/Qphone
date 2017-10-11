package com.example.a502.drawex;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

/**
 * Created by 502 on 2017-09-26.
 */

public class login extends Fragment {
    ViewGroup rootView;

    EditText idTxt;
    EditText pwTxt;
    TextView registerTxt;
    TextView findIdTxt;
    TextView findpwTxt;
    Button loginBtn;
    NavigationView navigationView;


    AppCompatActivity activity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ViewGroup)inflater.inflate(R.layout.login, container, false);

        idTxt=(EditText)rootView.findViewById(R.id.id);
        pwTxt=(EditText)rootView.findViewById(R.id.passward);
        registerTxt=(TextView)rootView.findViewById(R.id.register);
        findIdTxt=(TextView)rootView.findViewById(R.id.find_id);
        findpwTxt=(TextView)rootView.findViewById(R.id.find_pw);
        loginBtn = (Button) rootView.findViewById(R.id.loginBtn);

        activity = (AppCompatActivity) getActivity();


        //비밀번호 입력시 *로 뜨게
        pwTxt.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        pwTxt.setTransformationMethod(PasswordTransformationMethod.getInstance());

        //비밀번호 누르고 로그인버트안눌러두 되게게
        pwTxt.setImeOptions(EditorInfo.IME_ACTION_DONE);
        pwTxt.setOnEditorActionListener(new TextView.OnEditorActionListener()
        {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
            {
                if(actionId == EditorInfo.IME_ACTION_DONE)
                {
                    return true;
                }
                return false;
            }
        });

        navigationView=(NavigationView)activity.findViewById(R.id.navigation_view);



        loginBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if( idTxt.getText().toString().equals("1") && pwTxt.getText().toString().equals("1"))   //일반 손님 로그인 성공시
                {
                    Log.e("log1","normal login");
                    navigationView.getMenu().setGroupVisible(R.id.noLogin,false);
                    navigationView.getMenu().setGroupVisible(R.id.after_login_store,false);
                    navigationView.getMenu().setGroupVisible(R.id.after_login_normal,true);
                }
                if( idTxt.getText().toString().equals("2") && pwTxt.getText().toString().equals("2"))   //r관리자 로그인 성공시
                {
                    Log.e("log1","store login");
                    navigationView.getMenu().setGroupVisible(R.id.noLogin,false);
                    navigationView.getMenu().setGroupVisible(R.id.after_login_normal,false);
                    navigationView.getMenu().setGroupVisible(R.id.after_login_store,true);
                }

            }
        });


        findIdTxt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

        findpwTxt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

        registerTxt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                register r=new register();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame, r).commit();

            }
        });





        return rootView;

    }


}
