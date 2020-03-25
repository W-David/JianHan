package com.example.jianhan.util;

import android.content.Context;
import android.os.Environment;

import androidx.annotation.NonNull;

import java.io.File;

public class FileUtil {

    private static final String HTTP_CACHE_DIR = "http";

    @NonNull
    public static File getHttpCacheDir(@NonNull Context context) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return new File(context.getExternalCacheDir(), HTTP_CACHE_DIR);
        }
        return new File(context.getCacheDir(), HTTP_CACHE_DIR);
    }
}
