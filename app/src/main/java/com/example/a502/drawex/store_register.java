package com.example.a502.drawex;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by 502 on 2017-10-01.
 */

public class store_register extends Fragment {
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS=1;

    final int REQ_CODE_SELECT_IMAGE=100;

    ViewGroup rootView;
    AppCompatActivity activity;

    Button photoBtn;
    Button storeRegisterBtn;
    ImageView img;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootView = (ViewGroup)inflater.inflate(R.layout.store_register, container, false);

        activity=(AppCompatActivity)getActivity();

        photoBtn=(Button)rootView.findViewById(R.id.photoBtn);
        storeRegisterBtn=(Button)rootView.findViewById(R.id.store_register_btn);
        img=(ImageView)rootView.findViewById(R.id.imgView);



        photoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkPermission();  //접근 허용


            }
        });

        storeRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login_select l=new login_select();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame, l).commit();

            }
        });
        return rootView;
    }

    public void getPhoto()
    {
        Intent intent = new Intent(Intent.ACTION_PICK);

        File tempFile = new File(Environment.getExternalStorageDirectory() + "/temp.jpg");
        Uri tempUri = Uri.fromFile(tempFile);

        intent.putExtra("crop", "true");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);

        intent.setType("image/*");

        intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
        intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQ_CODE_SELECT_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Toast.makeText(activity, "resultCode : "+resultCode,Toast.LENGTH_SHORT).show();
        if(requestCode == REQ_CODE_SELECT_IMAGE) {

            if (resultCode == Activity.RESULT_OK) {
                try {
                    //Uri에서 이미지 이름을 얻어온다.
                    //String name_Str = getImageNameToUri(data.getData());
                    Uri uri=data.getData();
                    //이미지 데이터를 비트맵으로 받아온다.
                    Bitmap image_bitmap 	= MediaStore.Images.Media.getBitmap(activity.getContentResolver(), uri);

                    //배치해놓은 ImageView에 set
                    img.setImageBitmap(image_bitmap);

                    //Toast.makeText(getBaseContext(), "name_Str : "+name_Str , Toast.LENGTH_SHORT).show();


                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    Log.e("test3", e.getMessage());
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    Log.e("test1", e.getMessage());
                } catch (Exception e)
                {
                    e.getStackTrace();
                }

            }
        }



    }
    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(activity,android.Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED)
        {

            // Should we show an explanation?
            if (shouldShowRequestPermissionRationale(android.Manifest.permission.READ_CONTACTS))
            {
                // Explain to the user why we need to write the permission.
                Log.d("test", "WRITE_EXTERNAL_STORAGE");
            }

            requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_CONTACTS);
            getPhoto();

            // MY_PERMISSION_REQUEST_STORAGE is an
            // app-defined int constant


        }
        else
        {
            // 다음 부분은 항상 허용일 경우에 해당이 됩니다.
            getPhoto();

        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("test", "Permission granted");

                    // permission was granted, yay! do the
                    // calendar task you need to do.

                } else {

                    Log.d("test", "Permission always deny");

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                break;
        }
    }
}
