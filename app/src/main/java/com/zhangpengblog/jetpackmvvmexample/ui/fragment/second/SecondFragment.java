package com.zhangpengblog.jetpackmvvmexample.ui.fragment.second;

import androidx.lifecycle.ViewModelProvider;

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

public class SecondFragment extends Fragment {

    private SecondViewModel mViewModel;

    public static SecondFragment newInstance() {
        return new SecondFragment ();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate (R.layout.second_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated (view, savedInstanceState);
        TextView textView = view.findViewById (R.id.secondFragment_textView);
        textView.setOnClickListener (v ->
                ARouter.getInstance ()
                        .build ("/account/AccountActivity")
                        .withString ("action","I am from SecondFragment and I would like go to account module")
                        .withString ("title","SecondFragment->Account")
                        .navigation ()
        );
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated (savedInstanceState);
        mViewModel = new ViewModelProvider (this).get (SecondViewModel.class);
        // TODO: Use the ViewModel
    }

}