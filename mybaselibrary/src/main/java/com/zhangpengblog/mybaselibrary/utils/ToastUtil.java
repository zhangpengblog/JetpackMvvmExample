package com.zhangpengblog.mybaselibrary.utils;

import android.widget.Toast;

import com.zhangpengblog.mybaselibrary.BuildConfig;

public class ToastUtil {
    public static void show(String s) {
        Toast.makeText (Conteaxt.getContext (), s, Toast.LENGTH_SHORT).show ();
    }

    public static void debug(String s) {
        if (BuildConfig.DEBUG) {
            show ("debug: " + s);
        }
    }
}