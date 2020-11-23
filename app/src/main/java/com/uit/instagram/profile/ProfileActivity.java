package com.uit.instagram.profile;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.uit.instagram.R;
import com.uit.instagram.model.Photo;
import com.uit.instagram.utils.ViewCommentsFragment;
import com.uit.instagram.utils.ViewPostFragment;

public class ProfileActivity extends AppCompatActivity implements
        ProfileFragment.OnGridImageSelectedListener,
        ViewPostFragment.OnCommentThreadSelectedListener{

    private static final String TAG = "ProfileActivity";
    private static final int ACTIVITY_NUM = 4;
    private static final int NUM_GRID_COLUMN = 3;

    private Context mContext = ProfileActivity.this;
    private ImageView profilePhoto;

    @Override
    public void onGridImageSelected(Photo photo, int activityNumber) {
        Log.d(TAG, "onGridImageSelected: selected image gridview" + photo.toString());

        ViewPostFragment fragment = new ViewPostFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(getString(R.string.photo), photo);
        bundle.putInt(getString(R.string.activity_number), activityNumber);

        fragment.setArguments(bundle);

        FragmentTransaction transaction  = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(getString(R.string.view_post_fragment));
        transaction.commit();

    }

    @Override
    public void onCommentThreadSelectedListener(Photo photo) {
        Log.d(TAG, "onCommentThreadSelectedListener:  selected a comment thread");

        ViewCommentsFragment fragment = new ViewCommentsFragment();
        Bundle args = new Bundle();
        args.putParcelable(getString(R.string.photo), photo);
        fragment.setArguments(args);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(getString(R.string.view_comments_fragment));
        transaction.commit();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Log.d(TAG, "onCreate: starting");
        profilePhoto = (ImageView) findViewById(R.id.profile_photo);

        init();
    }

    private void init(){
        Log.d(TAG, "init: init" + getString(R.string.profile_fragment));

        ProfileFragment profileFragment = new ProfileFragment();
        FragmentTransaction transaction = ProfileActivity.this.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, profileFragment);
        transaction.addToBackStack(getString(R.string.profile_fragment));
        transaction.commit();
    }


}