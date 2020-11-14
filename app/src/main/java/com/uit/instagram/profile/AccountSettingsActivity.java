package com.uit.instagram.profile;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.uit.instagram.R;

/**
 * Setting app
 *
 * @author <VuKhanh> [18520903@gm.uit.edu.vn]
 * @since 11/14/2020
 */
public class AccountSettingsActivity extends AppCompatActivity {

    private static final String TAG = "AccountSettingsActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountsettings);
        Log.d(TAG, "onCreate: started");
    }
}
