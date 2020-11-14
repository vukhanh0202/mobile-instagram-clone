package com.uit.instagram.profile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.uit.instagram.R;
import com.uit.instagram.utils.UniversalImageLoader;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Edit profile fragment
 *
 * @author <VuKhanh> [18520903@gm.uit.edu.vn]
 * @since 11/14/2020
 */
public class EditProfileFragment extends Fragment {

    private static final String TAG = "EditProfileFragment";

    private CircleImageView mPhotoProfile;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_editprofile, container, false);
        mPhotoProfile = (CircleImageView) view.findViewById(R.id.profile_photo);

        initImageLoader();
        // Test set up photo profile
        UniversalImageLoader.setImage("d3av3o1z276gfa.cloudfront.net/images/place/r3B5IkCGEKReT7IcFWCIcVDSlPCXeXwr.jpeg",
                mPhotoProfile, null, "https://");

        // set up back arrow
        ImageView backArrow = (ImageView)view.findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: back to profile");
                (getActivity()).finish();
            }
        });

        return view;
    }

    private void initImageLoader(){
        UniversalImageLoader universalImageLoader = new UniversalImageLoader(getActivity());
        ImageLoader.getInstance().init(universalImageLoader.getConfig());
    }
}
