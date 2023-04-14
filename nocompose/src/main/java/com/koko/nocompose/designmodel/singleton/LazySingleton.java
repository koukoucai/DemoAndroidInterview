package com.koko.nocompose.designmodel.singleton;

/**
 * 懒汉式  无视 全部添加synchronized
 * Created by huanggang on 2022/11/4
 */
class LazySingleton {
    private LazySingleton(){};
    private static LazySingleton lazySingleton;

    public static synchronized LazySingleton getLazySingleton() {
        if (lazySingleton == null){
            lazySingleton = new LazySingleton();
        }
        return lazySingleton;
    }
}
