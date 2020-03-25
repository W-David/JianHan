package com.example.jianhan.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;

import com.example.jianhan.ui.activity.DetailActivity;
import com.example.jianhan.ui.activity.MainActivity;

import java.util.List;

public class IntentUtil {
    public static final String EXTRA_DETAIL = "extra_detail";

    public static void intentToDetailActivity(Activity activity, Parcelable parcelable){
        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putExtra(EXTRA_DETAIL,parcelable);
        activity.startActivity(intent);
    }

    public static void intentToShare(Activity activity,String share){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_HTML_TEXT,share);
        activity.startActivity(intent);
    }
}
