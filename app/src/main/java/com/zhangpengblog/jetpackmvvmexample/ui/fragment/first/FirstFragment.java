package com.zhangpengblog.jetpackmvvmexample.ui.fragment.first;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.zhangpengblog.jetpackmvvmexample.R;
import com.zhangpengblog.jetpackmvvmexample.http.Api;
import com.zhangpengblog.jetpackmvvmexample.http.retrofit.HolidayResponse;
import com.zhangpengblog.mybaselibrary.http.GetRetrofit;
import com.zhangpengblog.mybaselibrary.utils.MyLog;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Retrofit;

public class FirstFragment extends Fragment {
    private static final String TAG = "FirstFragment";
    private FirstViewModel mViewModel;
    private TextView dateTextView;
    public static FirstFragment newInstance() {
        return new FirstFragment ();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate (R.layout.first_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated (view, savedInstanceState);
        TextView textView = view.findViewById (R.id.firstFragment_textView);
        dateTextView =view.findViewById (R.id.firstFragment_date);
        textView.setOnClickListener (v ->
                        ARouter.getInstance ()
                                .build ("/account/AccountActivity")
                                .withString ("action","I am from FirstFragment and I would like go to account module")
                                .withString ("title","FirstFragment->Account")
                                .navigation ()
                );
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated (savedInstanceState);
        mViewModel = new ViewModelProvider (this).get (FirstViewModel.class);
        // TODO: Use the ViewModel
        @SuppressLint("SimpleDateFormat")
        String today = new SimpleDateFormat ("yyyy-MM-dd").format (new Date ().getTime ());
        mViewModel.isHoliday.observe (getViewLifecycleOwner (), aBoolean -> {
            if (aBoolean){
                dateTextView.setText (String.format ("%s is holiday", today));
            }else {
                dateTextView.setText (String.format ("%s is workday", today));
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart ();
        getHoliday();
    }

    private void getHoliday() {
        @SuppressLint("SimpleDateFormat")
        String today = new SimpleDateFormat ("yyyy-MM-dd").format (new Date ().getTime ());
        MyLog.d (TAG, "today: " + today);
        new HolidayResponse (GetRetrofit.getDate ().create (Api.class).getHoliday ("https://timor.tech/api/holiday/info/" + today)) {
            @Override
            protected void holiday() {
                mViewModel.setIsHoliday (true);
            }

            @Override
            protected void workDay() {
                mViewModel.setIsHoliday (false);
            }
        };
    }

}