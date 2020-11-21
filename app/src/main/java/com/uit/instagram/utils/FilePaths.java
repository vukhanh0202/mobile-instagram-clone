package com.uit.instagram.utils;

import android.os.Environment;

/**
 * @author <VuKhanh> [18520903@gm.uit.edu.vn]
 * @since 11/21/2020
 */
public class FilePaths {

    public String ROOT_DIR = Environment.getExternalStorageDirectory().getPath();

    public String PICTURES = ROOT_DIR + "/Pictures";
    public String CAMERA = ROOT_DIR + "/DCIM/camera";
    public String STORIES = ROOT_DIR + "/Stories";

    public String FIREBASE_STORY_STORAGE = "stories/users";
    public String FIREBASE_IMAGE_STORAGE = "photos/users/";

}
