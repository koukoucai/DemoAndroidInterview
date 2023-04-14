package com.koko.nocompose.designmodel.singleton;

/**
 * 静态内部类 单例
 * Created by huanggang on 2022/11/5
 */
public class StaticSingleton {
    private StaticSingleton(){};

    private static class SingletonHolder{
        private static StaticSingleton instance = new StaticSingleton();
    }

    public static StaticSingleton getInstance(){
        return SingletonHolder.instance;
    }
}
