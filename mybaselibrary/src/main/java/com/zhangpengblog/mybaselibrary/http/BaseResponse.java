package com.zhangpengblog.mybaselibrary.http;

import com.zhangpengblog.mybaselibrary.utils.MyLog;
import com.zhangpengblog.mybaselibrary.utils.ToastUtil;



import org.json.JSONException;

import java.io.IOException;
import java.util.Objects;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseResponse {
    private static final String TAG = "BaseResponse";
    private String json;

    public BaseResponse(final Call<ResponseBody> dataCall) {
        dataCall.enqueue (new Callback<ResponseBody> () {
            @Override
            public void onResponse( Call<ResponseBody> call,  Response<ResponseBody> response) {

                try {
                    MyLog.d (TAG, "onResponse: " + response.toString ());

                    json = response.body ().string ();
                    ONSUCCESS (json);


                } catch (IOException | JSONException e) {
                    ONERROR (json);
                    e.printStackTrace ();
                }
            }

            @Override
            public void onFailure( Call<ResponseBody> call,  Throwable t) {
                MyLog.d (TAG, "onFailure: " + t.getMessage ());
                if (Objects.requireNonNull (t.getMessage ()).contains ("10000ms")) {
                    ToastUtil.show ("网络超时");
                }
                ONFAIL (t);
            }
        });
    }

    public void ONSUCCESS(String json) throws JSONException {
    }

    public void ONERROR(String json) {
    }

    public void ONFAIL(Throwable t) {
    }
}