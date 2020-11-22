package com.uit.instagram.profile;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.uit.instagram.R;
import com.uit.instagram.utils.BottomNavigationViewUtil;
import com.uit.instagram.utils.FirebaseMethods;
import com.uit.instagram.utils.SectionsStatePagerAdapter;

import java.util.ArrayList;

/**
 * Setting app
 *
 * @author <VuKhanh> [18520903@gm.uit.edu.vn]
 * @since 11/14/2020
 */
public class AccountSettingsActivity extends AppCompatActivity {

    private static final String TAG = "AccountSettingsActivity";
    private static final int ACTIVITY_NUM = 4;

    private Context mContext;

    public SectionsStatePagerAdapter pagerAdapter;
    private ViewPager mViewPager;
    private RelativeLayout mRelativeLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountsettings);
        mContext = AccountSettingsActivity.this;
        mViewPager = (ViewPager) findViewById(R.id.viewpager_container);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.relLayout1);
        Log.d(TAG, "onCreate: started");

        setupSettingList();

        setupBottomNavigationView();
        setupFragments();
        getIncomingIntent();
        // setting up backarrow
        ImageView backArrow = (ImageView) findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: back to profile");
                finish();
            }
        });
    }

    private void getIncomingIntent() {

        Intent intent = getIntent();

        if(intent.hasExtra(getString(R.string.selected_image))
                || intent.hasExtra(getString(R.string.selected_bitmap))){

            //if there is an imageUrl attached as an extra, then it was chosen from the gallery/photo fragment
            Log.d(TAG, "getIncomingIntent: New incoming imgUrl");
            if(intent.getStringExtra(getString(R.string.return_to_fragment)).equals(getString(R.string.edit_profile_fragment))){

                if(intent.hasExtra(getString(R.string.selected_image))){
                    //set the new profile picture
                    FirebaseMethods firebaseMethods = new FirebaseMethods(AccountSettingsActivity.this);
                    firebaseMethods.uploadNewPhoto(getString(R.string.profile_photo), null, 0,
                            intent.getStringExtra(getString(R.string.selected_image)), null);
                }
                else if(intent.hasExtra(getString(R.string.selected_bitmap))){
                    //set the new profile picture
                    FirebaseMethods firebaseMethods = new FirebaseMethods(AccountSettingsActivity.this);
                    firebaseMethods.uploadNewPhoto(getString(R.string.profile_photo), null, 0,
                            null,(Bitmap) intent.getParcelableExtra(getString(R.string.selected_bitmap)));
                }

            }

        }

        if(intent.hasExtra(getString(R.string.calling_activity))){
            Log.d(TAG, "getIncomingIntent: received incoming intent from " + getString(R.string.profile_activity));
            setViewPager(pagerAdapter.getFragmentNumber(getString(R.string.edit_profile_fragment)));
        }
    }

    /**
     * Manage fragment of setting
     */
    private void setupFragments() {
        pagerAdapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new EditProfileFragment(), getString(R.string.edit_profile_fragment)); //fragment 0
        pagerAdapter.addFragment(new SignOutFragment(), getString(R.string.sign_out_fragment)); //fragment 1
    }

    /**
     * Set viewPager
     *
     * @param fragmentNumber
     */
    public void setViewPager(int fragmentNumber) {
        mRelativeLayout.setVisibility(View.GONE);
        Log.d(TAG, "setViewPager: navigating to fragment #: " + fragmentNumber);
        mViewPager.setAdapter(pagerAdapter);
        mViewPager.setCurrentItem(fragmentNumber);
    }

    /**
     * Setting up list of setting
     */
    private void setupSettingList() {
        Log.d(TAG, "setupSettingList: init account setting list");

        ListView listView = (ListView) findViewById(R.id.lvAccountSettings);

        ArrayList<String> options = new ArrayList<>();
        options.add(getString(R.string.edit_profile_fragment)); // fragment 0
        options.add(getString(R.string.sign_out_fragment));     // fragment 1

        ArrayAdapter adapter = new ArrayAdapter(mContext, android.R.layout.simple_list_item_1, options);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemClick: click fragment" + position);

                setViewPager(position);
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
