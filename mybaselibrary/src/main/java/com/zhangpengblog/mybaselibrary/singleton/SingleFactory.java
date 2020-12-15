package com.zhangpengblog.mybaselibrary.singleton;

public class SingleFactory {
    private static SingleFactory instance=new SingleFactory();
    private SingleFactory(){}
    public static  SingleFactory getInstance(){
        return instance;
    }


}
