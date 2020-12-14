package com.zhangpengblog.jetpackmvvmexample.ui.fragment.first;

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

import org.w3c.dom.Text;

public class FirstFragment extends Fragment {

    private FirstViewModel mViewModel;

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
    }

}