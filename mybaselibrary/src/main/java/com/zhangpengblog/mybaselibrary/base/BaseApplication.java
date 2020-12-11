package com.zhangpengblog.mybaselibrary.base;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.elvishew.xlog.LogConfiguration;
import com.elvishew.xlog.LogLevel;
import com.elvishew.xlog.XLog;
import com.zhangpengblog.mybaselibrary.BuildConfig;
import com.zhangpengblog.mybaselibrary.utils.Conteaxt;

import org.xutils.x;

public class BaseApplication extends Application {
    private final static boolean isDebugARouter = BuildConfig.DEBUG;

    @Override
    public void onCreate() {
        super.onCreate ();
        Conteaxt.init (this);
        //ARoute 调试开关

        if (isDebugARouter) {

            ARouter.openLog ();//打印日志
            //开启调试模式（如果在InstantRun模式下运行，必须开启调试模式，线上版本需要关闭）
            ARouter.openDebug ();
        }
        ARouter.init (this);
        x.Ext.init (this);
        LogConfiguration config = new LogConfiguration.Builder ()
                .logLevel (BuildConfig.DEBUG ? LogLevel.ALL             // 指定日志级别，低于该级别的日志将不会被打印，默认为 LogLevel.ALL
                        : LogLevel.NONE)
                .tag ("BeeGame")                                         // 指定 TAG，默认为 "X-LOG"
//                .b()                                                   // 允许打印日志边框，默认禁止
                .build ();
        XLog.init (config);


    }
}