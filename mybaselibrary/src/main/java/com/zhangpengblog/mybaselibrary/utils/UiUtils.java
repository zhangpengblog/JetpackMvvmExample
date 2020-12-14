package com.zhangpengblog.mybaselibrary.utils;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.KeyEvent;

import androidx.core.content.ContextCompat;

import com.elvishew.xlog.XLog;
import com.zhangpengblog.mybaselibrary.R;

import cn.ycbjie.ycstatusbarlib.bar.StateAppBar;
import cn.ycbjie.ycstatusbarlib.utils.StatusBarUtils;


public class UiUtils {
    private static final String TAG="UiUtils";
    public static void setStatusBarTransparent(Activity activity, boolean b) {
        if (b) {
            StateAppBar.setStatusBarColor (activity,
                    ContextCompat.getColor (activity, R.color.transparent));
            StatusBarUtils.StatusBarLightMode (activity);
        } else {
            StateAppBar.setStatusBarColor (activity,
                    ContextCompat.getColor (activity, R.color.design_default_color_primary_dark));
        }
    }
    public static void GoBack() {
        new Thread () {
            public void run() {
                try {
                    Instrumentation instrument = new Instrumentation ();
                    instrument.sendKeyDownUpSync (KeyEvent.KEYCODE_BACK);
                } catch (Exception e) {

                }
            }
        }.start ();

    }

    /**
     * 获取屏幕的像素大小，去掉状态栏高度
     * @param activity
     * @return
     */
    public static Point GetDeviceSize(Activity activity){
        DisplayMetrics outMetrics = new DisplayMetrics ();
        activity.getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        int widthPixels = outMetrics.widthPixels;
        int heightPixels = outMetrics.heightPixels;
        int statusBarHeight = getStatusBarHeight (activity);
        XLog.i(TAG, "widthPixels = " + widthPixels + ",heightPixels = " + (heightPixels-statusBarHeight));
        return new Point (widthPixels,heightPixels-statusBarHeight);
    }

    /**
     * 获取状态栏高度
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        return resources.getDimensionPixelSize(resourceId);
    }
}
