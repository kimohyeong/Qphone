package com.example.a502.drawex;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

    SharedPreferences appData;
    SharedPreferences.Editor editor;

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

        appData=activity.getSharedPreferences("appData", Context.MODE_PRIVATE);


        //비밀번호 입력시 *로 뜨게
        pwTxt.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        pwTxt.setTransformationMethod(PasswordTransformationMethod.getInstance());

        //비밀번호 누르고 로그인버트안눌러두 되게
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

        //번들로 넘어오는부분 ★★★★★★★★★★저어어엉고오오오으으으은 이부분임!!!!!
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            int i = bundle.getInt("check");
            int checkLogout = bundle.getInt("logout");
            if(checkLogout==1)
            {
                navigationView.getMenu().setGroupVisible(R.id.noLogin,true);
                navigationView.getMenu().setGroupVisible(R.id.after_login_store,false);
                navigationView.getMenu().setGroupVisible(R.id.after_login_normal,false);

            }
            else
            {
                idTxt.setText(i+"");
                pwTxt.setText(i+"");

                if(bundle.getBoolean("bLogin",false))
                {
                    Log.i("login","로그인 기록 있음");
                    if(appData.getInt("type",0)==1)     //일반회원으로 로그인했음
                    {
                        navigationView.getMenu().setGroupVisible(R.id.noLogin,false);
                        navigationView.getMenu().setGroupVisible(R.id.after_login_store,false);
                        navigationView.getMenu().setGroupVisible(R.id.after_login_normal,true);

                        normal_home n=new normal_home();
                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame, n).commit();
                    }
                    else        //가게로 로그인 했음
                    {
                        Log.e("log1","store login");
                        navigationView.getMenu().setGroupVisible(R.id.noLogin,false);
                        navigationView.getMenu().setGroupVisible(R.id.after_login_normal,false);
                        navigationView.getMenu().setGroupVisible(R.id.after_login_store,true);
                    }
                }
            }

        }


        loginBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if( idTxt.getText().toString().equals("1") && pwTxt.getText().toString().equals("1"))   //일반 손님 로그인 성공시
                {
                    Log.e("log1","normal login");
                    navigationView.getMenu().setGroupVisible(R.id.noLogin,false);
                    navigationView.getMenu().setGroupVisible(R.id.after_login_store,false);
                    navigationView.getMenu().setGroupVisible(R.id.after_login_normal,true);

                    normal_home n=new normal_home();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame, n).commit();

                    editor=appData.edit();
                    editor.putBoolean("SAVE_LOGIN_DATA",true);
                    editor.putString("ID",idTxt.getText().toString());
                    editor.putString("PW",pwTxt.getText().toString());
                    editor.putInt("type",1);
                    editor.apply();
                }
                if( idTxt.getText().toString().equals("2") && pwTxt.getText().toString().equals("2"))   //r관리자 로그인 성공시
                {
                    Log.e("log1","store login");
                    navigationView.getMenu().setGroupVisible(R.id.noLogin,false);
                    navigationView.getMenu().setGroupVisible(R.id.after_login_normal,false);
                    navigationView.getMenu().setGroupVisible(R.id.after_login_store,true);

                    editor=appData.edit();
                    editor.putBoolean("SAVE_LOGIN_DATA",true);
                    editor.putString("ID",idTxt.getText().toString());
                    editor.putString("PW",pwTxt.getText().toString());
                    editor.putInt("type",2);
                    editor.apply();

                    store_mystore s=new store_mystore();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame, s).commit();

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
                register_select r=new register_select();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame, r).commit();

            }
        });





        return rootView;

    }


}
