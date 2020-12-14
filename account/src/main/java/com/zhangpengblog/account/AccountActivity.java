package com.zhangpengblog.account;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zhangpengblog.mybaselibrary.base.BaseAppActivity;
import com.zhangpengblog.mybaselibrary.utils.ToastUtil;
import com.zhangpengblog.mybaselibrary.utils.UiUtils;

@Route(path = "/account/AccountActivity")
public class AccountActivity extends BaseAppActivity {
    @Autowired
    String action;
    @Autowired
    String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance ().inject (this);
        UiUtils.setStatusBarTransparent (this,true);
        setBaseContentView(R.layout.activity_account);
        AccountViewModel viewModel = new ViewModelProvider (this).get (AccountViewModel.class);
        viewModel.set_action (action);
        viewModel.set_title (title);
        viewModel.action.observe (this, new Observer<String> () {
            @Override
            public void onChanged(String s) {
                ToastUtil.debug (s);
            }
        });
        viewModel.title.observe (this, new Observer<String> () {
            @Override
            public void onChanged(String s) {
                setBaseTitle (s);
            }
        });



    }
}