package com.example.a502.drawex;

import android.support.v4.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private BackPressCloseHandler backPressCloseHandler;

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, loading.class);
        startActivity(intent);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);

        backPressCloseHandler = new BackPressCloseHandler(this);


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();

                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.navigation_item_login:
                        Toast.makeText(MainActivity.this, menuItem.getTitle(), Toast.LENGTH_LONG).show();
                        login_select l=new login_select();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame, l).commit();
                        break;

                    case R.id.navigation_item_register:
                        Toast.makeText(MainActivity.this, menuItem.getTitle(), Toast.LENGTH_LONG).show();
                        register_select r=new register_select();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame, r).commit();
                        break;

                    case R.id.navigation_item_home:
                        Toast.makeText(MainActivity.this, menuItem.getTitle(), Toast.LENGTH_LONG).show();
                        normal_home n=new normal_home();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame, n).commit();
                        break;

                    case R.id.navigation_item_setting:
                        Toast.makeText(MainActivity.this, menuItem.getTitle(), Toast.LENGTH_LONG).show();
                       // login l=new login();
                       // getSupportFragmentManager().beginTransaction().replace(R.id.frame, l).commit();
                        break;
                    case R.id.navigation_item_logout:
                        Toast.makeText(MainActivity.this, menuItem.getTitle(), Toast.LENGTH_LONG).show();
                        login l2=new login();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame, l2).commit();
                        break;

                    case R.id.navigation_item_mystore:
                        Toast.makeText(MainActivity.this, menuItem.getTitle(), Toast.LENGTH_LONG).show();
                        store_mystore sm=new store_mystore();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame, sm).commit();
                        break;
                    case R.id.navigation_item_coupon_custom:
                        Toast.makeText(MainActivity.this, menuItem.getTitle(), Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(getApplicationContext(), store_custom1.class);
                        //로그인 한 사람의 id정보(가게 ID)를 extra로 전송해야할 것 같다.
                        startActivity(intent);
                        break;
                    case R.id.navigation_item_notice:
                        Toast.makeText(MainActivity.this, menuItem.getTitle(), Toast.LENGTH_LONG).show();
                        store_notice sn=new store_notice();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame, sn).commit();
                        break;
                    case R.id.navigation_item_store_register:
                        Toast.makeText(MainActivity.this, menuItem.getTitle(), Toast.LENGTH_LONG).show();
                        store_register sr=new store_register();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame, sr).commit();
                        break;

                }

                return true;
            }
        });

        login_select l=new login_select();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, l).commit();


    }

    @Override public void onBackPressed()
    {
        backPressCloseHandler.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.action_settings:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}