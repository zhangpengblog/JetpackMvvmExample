package com.zhangpengblog.mybaselibrary.http;

import com.zhangpengblog.mybaselibrary.singleton.SingletonEnum;

import retrofit2.Retrofit;

public class GetRetrofit {
    
    public static Retrofit getDate(){
        SingletonEnum single=SingletonEnum.INSTANCE;
        String date="https://timor.tech/api/holiday/info/";
        return single.getRetorfit (date);
    }

}