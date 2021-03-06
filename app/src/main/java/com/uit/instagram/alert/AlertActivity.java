package com.uit.instagram.alert;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.uit.instagram.R;
import com.uit.instagram.home.HomeActivity;
import com.uit.instagram.utils.BottomNavigationViewUtil;

public class AlertActivity extends AppCompatActivity {

    private static final String TAG = "AlertActivity";
    private static final int ACTIVITY_NUM = 3;

    private Context mContext = AlertActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: starting");

        setupBottomNavigationView();
    }

    /**
     * Bottom navigationView setup
     */
    private void setupBottomNavigationView(){
        Log.d(TAG, "setupBottomNavigationView: setting up bottom navigationView");
        BottomNavigationViewEx bottomNavigationView = findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewUtil.enableNavigation(mContext,this, bottomNavigationView);

        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }
}