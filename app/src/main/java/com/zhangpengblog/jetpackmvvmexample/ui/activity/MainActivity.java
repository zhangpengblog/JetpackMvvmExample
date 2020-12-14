package com.zhangpengblog.jetpackmvvmexample.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.zhangpengblog.jetpackmvvmexample.R;
import com.zhangpengblog.jetpackmvvmexample.ui.fragment.first.FirstFragment;
import com.zhangpengblog.jetpackmvvmexample.ui.fragment.second.SecondFragment;
import com.zhangpengblog.jetpackmvvmexample.ui.fragment.third.ThirdFragment;
import com.zhangpengblog.mybaselibrary.base.BaseAppActivity;
import com.zhangpengblog.mybaselibrary.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;

@Route(path = "/app/MainActivity")
public class MainActivity extends BaseAppActivity {

    private BottomNavigationView bottomNavigationView;
    private List<Fragment> mFragments = new ArrayList<> ();
    private FirstFragment firstFragment;
    private SecondFragment secondFragment;
    private ThirdFragment thirdFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setBaseContentViewWithoutTitle (R.layout.activity_main);
//        hideActionBar();
        UiUtils.setStatusBarTransparent (this,true);
        firstFragment = FirstFragment.newInstance ();
        setSelectedFragment (firstFragment);
        bottomNavigationView = findViewById (R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener (menuItem -> {
            switch (menuItem.getItemId ()) {
                case R.id.firstFragment:
                    if (firstFragment == null) {
                        firstFragment = FirstFragment.newInstance ();
                    } else if (bottomNavigationView.getSelectedItemId () == R.id.firstFragment) {//只有在首页的时候点击下面的图标刷新首页
                        firstFragment = FirstFragment.newInstance ();
                    }
                    setSelectedFragment (firstFragment);
                    break;
                case R.id.secondFragment:
                    if (secondFragment == null) {
                        secondFragment = SecondFragment.newInstance ();
                    }
                    setSelectedFragment (secondFragment);
                    break;
                case R.id.thirdFragment:
                    if (thirdFragment == null) {
                        thirdFragment = ThirdFragment.newInstance ();
                    }
                    setSelectedFragment (thirdFragment);
                    break;
            }
            return true;
        });
    }

    public void setSelectedFragment(Fragment fragment) {
        addFragment (fragment);
        showFragment (fragment);
    }

    /*添加fragment*/
    private void addFragment(Fragment fragment) {

        /*判断该fragment是否已经被添加过  如果没有被添加  则添加*/
        if (!fragment.isAdded ()) {
            getSupportFragmentManager ().beginTransaction ().add (R.id.fragment, fragment).commit ();
            /*添加到 fragmentList*/
            mFragments.add (fragment);
        }


    }

    /*显示fragment*/
    private void showFragment(Fragment fragment) {

        for (Fragment frag : mFragments) {

            if (frag != fragment) {
                /*先隐藏其他fragment*/
                getSupportFragmentManager ().beginTransaction ().hide (frag).commit ();
            }
        }
        getSupportFragmentManager ().beginTransaction ().show (fragment).commit ();

    }
}