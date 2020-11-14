package com.uit.instagram.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.uit.instagram.R;

/**
 *  Fragment camera
 *
 * @author <VuKhanh> [18520903@gm.uit.edu.vn]
 * @since 11/14/2020
 */
public class MessageFragment extends Fragment {
    private static final String TAG = "MessageFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message,container, false);

        return view;
    }
}
