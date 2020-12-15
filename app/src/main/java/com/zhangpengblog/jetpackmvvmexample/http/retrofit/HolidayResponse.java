package com.zhangpengblog.jetpackmvvmexample.http.retrofit;

import com.google.gson.Gson;
import com.zhangpengblog.mybaselibrary.bean.DateBean;
import com.zhangpengblog.mybaselibrary.http.BaseResponse;
import com.zhangpengblog.mybaselibrary.utils.MyLog;

import org.json.JSONException;

import okhttp3.ResponseBody;
import retrofit2.Call;

public abstract class HolidayResponse extends BaseResponse {
    public HolidayResponse(Call<ResponseBody> dataCall) {
        super (dataCall);
    }

    @Override
    public void ONSUCCESS(String json) throws JSONException {
        super.ONSUCCESS (json);
        MyLog.json ("Holiday", json);
        DateBean dateBean=new Gson ().fromJson (json,DateBean.class);
        if (dateBean.getCode ()==0){
            if (dateBean.getType ().getType ()==0){
                workDay();
            }else {
                holiday();
            }
        }
    }

    protected abstract void holiday();

    protected abstract void workDay();

    @Override
    public void ONERROR(String json) {
        super.ONERROR (json);
    }
}