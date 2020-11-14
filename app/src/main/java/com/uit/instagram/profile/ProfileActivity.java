package com.uit.instagram.profile;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.uit.instagram.R;
import com.uit.instagram.utils.BottomNavigationViewUtil;
import com.uit.instagram.utils.GridImageAdapter;
import com.uit.instagram.utils.UniversalImageLoader;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    private static final String TAG = "ProfileActivity";
    private static final int ACTIVITY_NUM = 4;
    private static final int NUM_GRID_COLUMN = 3;

    private Context mContext = ProfileActivity.this;
    private ImageView profilePhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Log.d(TAG, "onCreate: starting");
        profilePhoto = (ImageView) findViewById(R.id.profile_photo);

        setupBottomNavigationView();
        setupToolbar();
        initImageLoader();
        setProfileImage();

        DataImageTest();
    }
    private void initImageLoader(){
        UniversalImageLoader universalImageLoader = new UniversalImageLoader(mContext);
        ImageLoader.getInstance().init(universalImageLoader.getConfig());
    }
    private void setProfileImage(){
        UniversalImageLoader.setImage("d3av3o1z276gfa.cloudfront.net/images/place/r3B5IkCGEKReT7IcFWCIcVDSlPCXeXwr.jpeg",
                profilePhoto, null, "https://");
    }

    private void DataImageTest(){
        ArrayList<String> imgURLs = new ArrayList<>();
        imgURLs.add("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcT2eiwZOwsYzwa5HZlqaA1tNvfe1FoxhvMmwA&usqp=CAU");
        imgURLs.add("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQS7dB16Hz8M5lzYc1jR8PJ8-V9Xdan-tjsqQ&usqp=CAU");
        imgURLs.add("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSc-G92Y30n32LzsoZQhtTXVkc0oJ4vNwNdLw&usqp=CAU");
        imgURLs.add("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSSxk3xFiRdaLMDobafEZufYauwS9ZnPRaqHQ&usqp=CAU");
        imgURLs.add("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcT2eiwZOwsYzwa5HZlqaA1tNvfe1FoxhvMmwA&usqp=CAU");
        imgURLs.add("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQS7dB16Hz8M5lzYc1jR8PJ8-V9Xdan-tjsqQ&usqp=CAU");
        imgURLs.add("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSc-G92Y30n32LzsoZQhtTXVkc0oJ4vNwNdLw&usqp=CAU");
        imgURLs.add("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSSxk3xFiRdaLMDobafEZufYauwS9ZnPRaqHQ&usqp=CAU");
        imgURLs.add("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcT2eiwZOwsYzwa5HZlqaA1tNvfe1FoxhvMmwA&usqp=CAU");
        imgURLs.add("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQS7dB16Hz8M5lzYc1jR8PJ8-V9Xdan-tjsqQ&usqp=CAU");
        imgURLs.add("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSc-G92Y30n32LzsoZQhtTXVkc0oJ4vNwNdLw&usqp=CAU");
        imgURLs.add("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSSxk3xFiRdaLMDobafEZufYauwS9ZnPRaqHQ&usqp=CAU");

        setupImageGrid(imgURLs);
    }

    /**
     * Image grid display image of user
     */
    private void setupImageGrid(ArrayList<String> imgURLs){
        GridView gridView = (GridView)findViewById(R.id.gridView);

        int gridWidth = getResources().getDisplayMetrics().widthPixels;
        int imageWidth = gridWidth / NUM_GRID_COLUMN;
        gridView.setColumnWidth(imageWidth);

        GridImageAdapter adapter = new GridImageAdapter(mContext, R.layout.layout_grid_imageview,"",imgURLs);
        gridView.setAdapter(adapter);
    }

    /**
     * Set up toolbar for profile
     */
    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.profileToolBar);
        setSupportActionBar(toolbar);


        ImageView profileMenu = (ImageView) findViewById(R.id.profileMenu);
        profileMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: options is clicked");
                Intent intent = new Intent(mContext, AccountSettingsActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Bottom navigationView setup
     */
    private void setupBottomNavigationView() {
        Log.d(TAG, "setupBottomNavigationView: setting up bottom navigationView");
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewUtil.enableNavigation(mContext, bottomNavigationView);

        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }

}