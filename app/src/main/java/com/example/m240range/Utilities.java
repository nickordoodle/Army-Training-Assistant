package com.example.m240range;

import android.content.ContentResolver;
import android.net.Uri;

import java.io.File;

public class Utilities {

    public static Uri getRawUri(String filename) {
        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + File.pathSeparator + File.separator
                + "/raw/" + filename);
    }
    public Uri getDrawableUri(String filename) {
        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + File.pathSeparator + File.separator + MainActivity.PACKAGE_NAME + "/drawable/" + filename);
    }
    public Uri getMipmapUri(String filename) {
        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + File.pathSeparator + File.separator + MainActivity.PACKAGE_NAME + "/mipmap/" + filename);
    }

}
