package com.uit.instagram.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.uit.instagram.R;
import com.uit.instagram.alert.AlertActivity;
import com.uit.instagram.home.HomeActivity;
import com.uit.instagram.profile.ProfileActivity;
import com.uit.instagram.search.SearchActivity;
import com.uit.instagram.share.ShareActivity;

/**
 * Utils for navigation bottom
 *
 * @author <VuKhanh> [18520903@gm.uit.edu.vn]
 * @since 11/14/2020
 */
public class BottomNavigationViewUtil {

    private static final String TAG = "BottomNavigationView";

    public static void enableNavigation(final Context context, final Activity callingActivity, BottomNavigationViewEx view) {
        view.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.ic_house:
                    Intent intent1 = new Intent(context, HomeActivity.class);//ACTIVITY_NUM = 0
                    context.startActivity(intent1);
                    callingActivity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    if (!(callingActivity instanceof HomeActivity))
                        callingActivity.finish();
                    break;

                case R.id.ic_search:
                    Intent intent2 = new Intent(context, SearchActivity.class);//ACTIVITY_NUM = 1
                    context.startActivity(intent2);
                    callingActivity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    if (!(callingActivity instanceof SearchActivity))
                        callingActivity.finish();
                    break;

                case R.id.ic_circle:
                    Intent intent3 = new Intent(context, ShareActivity.class);//ACTIVITY_NUM = 2
                    context.startActivity(intent3);
                    callingActivity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    if (!(callingActivity instanceof ShareActivity))
                        callingActivity.finish();
                    break;

                case R.id.ic_profile:
                    Intent intent5 = new Intent(context, ProfileActivity.class);//ACTIVITY_NUM = 4
                    context.startActivity(intent5);
                    callingActivity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    if (!(callingActivity instanceof ProfileActivity))
                        callingActivity.finish();
                    break;
            }
            return false;
        });
    }
}
