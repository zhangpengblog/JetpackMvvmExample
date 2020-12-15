package com.zhangpengblog.jetpackmvvmexample.http;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface Api {
    @GET
    Call<ResponseBody> getHoliday(@Url String url);
}
