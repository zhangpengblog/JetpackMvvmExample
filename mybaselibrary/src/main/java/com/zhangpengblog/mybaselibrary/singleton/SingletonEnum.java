package com.zhangpengblog.mybaselibrary.singleton;

import com.zhangpengblog.mybaselibrary.http.MyInterceptor;
import com.zhangpengblog.mybaselibrary.utils.MyLog;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public enum SingletonEnum {
    INSTANCE;
    private static final String TAG = "EnumSingle";

    public Retrofit getRetorfit(String baseurl) {
        MyLog.d (TAG, "getRetorfit: ");

        OkHttpClient client = new OkHttpClient ();
        OkHttpClient.Builder builder = new OkHttpClient.Builder ();
        builder.addInterceptor (new MyInterceptor ());
        builder.readTimeout (30, TimeUnit.SECONDS);
        builder.connectTimeout (30, TimeUnit.SECONDS);
        builder.writeTimeout (30, TimeUnit.SECONDS);
        client = builder.build ();


        return new Retrofit.Builder ()
                //设置网络请求BaseUrl地址
                .baseUrl (baseurl)
                //设置数据解析器
                .addConverterFactory (GsonConverterFactory.create ())
                .client (client)
                .build ();
    }
}
