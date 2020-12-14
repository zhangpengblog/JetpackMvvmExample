package com.zhangpengblog.mybaselibrary.base;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleObserver;

import com.zhangpengblog.mybaselibrary.R;
import com.zhangpengblog.mybaselibrary.utils.MyLog;
import com.zhangpengblog.mybaselibrary.utils.UiUtils;

import cn.ycbjie.ycstatusbarlib.bar.StateAppBar;
import cn.ycbjie.ycstatusbarlib.utils.StatusBarUtils;

public class BaseAppActivity extends FragmentActivity implements LifecycleObserver {
    private static final String TAG = "BaseAppActivity";
    public Activity context;
    private LinearLayout linearLayout;
    private RelativeLayout back, more, bar;
    private TextView baseTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        try {

            setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            context = this;
            setContentView (R.layout.activity_base);
            linearLayout = findViewById (R.id.base_layout);
            back = findViewById (R.id.back);
            more = findViewById (R.id.more);
            baseTitle = findViewById (R.id.base_title);
            bar = findViewById (R.id.bar);
            back.setOnClickListener (new View.OnClickListener () {
                @Override
                public void onClick(View view) {
                    UiUtils.GoBack ();
                    MyLog.d (TAG, "onClick: GoBack");
                }
            });

        } catch (Exception e) {
            MyLog.e (TAG, e.getMessage ());
        }

    }


//    /**
//     * 设置状态栏颜色
//     * 没什么卵用，不要用
//     *
//     * @param resId
//     */
//    public void setStatusBarColor(int resId) {
//        StatusBarUtil.immersive(this, resId);
//    }
    public void hideActionBar(){
//        ActionBar actionBar = getSupportActionBar ();
//        actionBar.hide ();//隐藏掉AppCompatActivity自带的ActionBar
        StateAppBar.setStatusBarColor (this, ContextCompat.getColor (this, R.color.transparent));
        StatusBarUtils.StatusBarLightMode (this);
    }


    public RelativeLayout setBackAction() {
        back.setOnClickListener (null);
        return back;
    }

    public View getRootView() {
        return linearLayout;
    }


    /**
     * 页面内容,根布局使用LinearLayout,根布局使用LinearLayout,根布局使用LinearLayout,
     *
     * @param layoutId
     */
    public void setBaseContentView(int layoutId) {
        //当子布局高度值不足ScrollView时，用这个方法可以充满ScrollView，防止布局无法显示
        FrameLayout layout = findViewById(R.id.base_main_layout);
        //获取布局，并在BaseActivity基础上显示
        final View view = getLayoutInflater().inflate(layoutId, null);
        //关闭键盘
        hideKeyBoard();
        //给EditText的父控件设置焦点，防止键盘自动弹出
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        Point point= UiUtils.GetDeviceSize (this);
        MyLog.d (TAG, "onCreate: " + getLocalClassName ());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                point.x, point.y);
        layout.addView(view, params);
    }

    /**
     * 底部有固定view，给底部view留出空间
     * @param layoutId
     */
    public void setBaseContentViewWithoutTitle(int layoutId) {
        //当子布局高度值不足ScrollView时，用这个方法可以充满ScrollView，防止布局无法显示
        bar.setVisibility (View.GONE);
        FrameLayout layout = findViewById(R.id.base_main_layout);
        //获取布局，并在BaseActivity基础上显示
        final View view = getLayoutInflater().inflate(layoutId, null);
        //关闭键盘
        hideKeyBoard();
        //给EditText的父控件设置焦点，防止键盘自动弹出
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        Point point= UiUtils.GetDeviceSize (this);
        MyLog.d (TAG, "onCreate: " + getLocalClassName ());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                point.x, point.y);
        layout.addView(view, params);
    }

    /**
     * 隐藏键盘
     */
    public void hideKeyBoard() {
        View view = ((Activity) context).getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * 设置bar颜色
     * @param id
     */
    public void setBarBackground(int id){
        linearLayout.setBackgroundResource(id);
    }

    /**
     * 设置标题
     *
     * @param title 标题的文本
     */
    public void setBaseTitle(String title) {
        baseTitle.setText(title);
    }

    /**
     * 设置右侧图标
     */
    public void setBaseBarRightIcon(int resId) {
        more.setBackgroundResource(resId);
    }

    /**
     * 设置bar颜色
     *
     * @param resId
     */
    public void setBaseBarColor(int resId) {
        bar.setBackgroundResource(resId);
    }

    public void setBarVisiable(int visiable) {
        if (visiable == View.VISIBLE) {
            bar.setVisibility(View.VISIBLE);
        } else if (visiable == View.GONE) {
            bar.setVisibility(View.GONE);
        } else if (visiable == View.INVISIBLE) {
            bar.setVisibility(View.INVISIBLE);
        } else {
            return;
        }
    }

    /**
     * 是否显示more按钮
     *
     * @param visiablele
     */
    public void setBaseMoreActionVisiable(boolean visiablele) {
        if (visiablele) {
            more.setVisibility(View.VISIBLE);
        } else {
            more.setVisibility(View.GONE);
        }
    }

    /**
     * 更多功能按键。常用于设置clicklistener
     *
     * @return
     */
    public View getBaseBarMoreActionBack() {
        return more;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy ();
        MyLog.d (TAG, "onDestroy: " + getClass ().getName ());
    }

    @Override
    protected void onPause() {
        super.onPause ();
        MyLog.d (TAG, "onPause: " + getClass ().getName ());
    }

    @Override
    protected void onResume() {
        super.onResume ();
        MyLog.d (TAG, "onResume: " + getClass ().getName ());
    }

    @Override
    protected void onStop() {
        super.onStop ();
        MyLog.d (TAG, "onStop: " + getClass ().getName ());
    }

    @Override
    protected void onRestart() {
        super.onRestart ();
        MyLog.d (TAG, "onRestart: " + getClass ().getName ());
    }

//    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
//    protected void pasueLifecycle(){
//        MyLog.d(TAG, "pasue: "+getClass().getName());
//    }
//
//    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
//    protected void resumeLifecycle(){
//        MyLog.d(TAG, "resume: "+getClass().getName());
//    }
//
//    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
//    protected void stopLifecycle(){
//        MyLog.d(TAG, "stop: "+getClass().getName());
//    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction () == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus ();
            if (isShouldHideInput (v, ev)) {//点击editText控件外部
                InputMethodManager imm = (InputMethodManager) getSystemService (Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    assert v != null;
                    closeKeyboard (v);//软键盘工具类
                    if (editText != null) {
                        editText.clearFocus ();
                    }
                }
            }
            return super.dispatchTouchEvent (ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        return getWindow ().superDispatchTouchEvent (ev) || onTouchEvent (ev);
    }

    EditText editText = null;

    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            editText = (EditText) v;
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow (leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight ();
            int right = left + v.getWidth ();
            return !(event.getX () > left && event.getX () < right
                    && event.getY () > top && event.getY () < bottom);
        }
        return false;
    }

    public static void closeKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext ().getSystemService (Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow (view.getWindowToken (), InputMethodManager.HIDE_NOT_ALWAYS);
    }

}
